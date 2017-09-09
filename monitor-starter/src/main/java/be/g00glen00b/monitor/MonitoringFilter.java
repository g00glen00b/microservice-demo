package be.g00glen00b.monitor;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.filter.OncePerRequestFilter;

public class MonitoringFilter extends OncePerRequestFilter {
    private static final String REQUEST_ERROR_LABEL = "counter.requests.errors";
    private static final String REQUEST_AMOUNT_LABEL = "counter.requests.amount";
    private static final String REQUEST_AVERAGE_LABEL = "gauge.requests.avg";
    private CounterService counterService;
    private GaugeService gaugeService;
    private Map<String, Long> averages = new ConcurrentHashMap<>();
    private Map<String, Integer> counts = new ConcurrentHashMap<>();

    public MonitoringFilter(CounterService counterService, GaugeService gaugeService) {
        this.counterService = counterService;
        this.gaugeService = gaugeService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        counterService.increment(REQUEST_AMOUNT_LABEL);
        long start = System.nanoTime();
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        if (httpServletResponse.getStatus() >= HttpStatus.BAD_REQUEST.value()) {
            counterService.increment(REQUEST_ERROR_LABEL);
        }
        updateResponseTime(httpServletRequest.getServletPath(), start, System.nanoTime());
    }

    private void updateResponseTime(String pathInfo, long start, long end) {
        String suffix = pathInfo.replaceAll("/", ".");
        int newCount = counts.getOrDefault(suffix, 0) + 1;
        long newAverage = ((averages.getOrDefault(suffix, 0L) * (newCount - 1)) + (end - start)) / newCount;
        averages.put(suffix, newAverage);
        counts.put(suffix, newCount);
        gaugeService.submit(REQUEST_AVERAGE_LABEL + suffix, newAverage);
    }

    @Scheduled(fixedDelayString = "${monitoring.interval:10}")
    public void reset() {
        counterService.reset(REQUEST_AMOUNT_LABEL);
        counterService.reset(REQUEST_ERROR_LABEL);
        counts.clear();
        averages.clear();
    }
}
