package be.g00glen00b.monitor;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class MonitoringFilter extends OncePerRequestFilter {
    private static final String REQUEST_ERROR_LABEL = "counter.requests.errors";
    private static final String REQUEST_AMOUNT_LABEL = "counter.requests.amount";
    private static final String REQUEST_AVERAGE_LABEL = "gauge.response.avg.";
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private CounterService counterService;
    private GaugeService gaugeService;
    private MonitoringProperties monitoringProperties;
    private Map<MonitoringPathMatcher, Long> averages = new ConcurrentHashMap<>();
    private Map<MonitoringPathMatcher, Integer> counts = new ConcurrentHashMap<>();

    public MonitoringFilter(CounterService counterService, GaugeService gaugeService, MonitoringProperties monitoringProperties) {
        this.counterService = counterService;
        this.gaugeService = gaugeService;
        this.monitoringProperties = monitoringProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        counterService.increment(REQUEST_AMOUNT_LABEL);
        long start = System.nanoTime();
        filterChain.doFilter(httpServletRequest, httpServletResponse);
        if (httpServletResponse.getStatus() >= HttpStatus.BAD_REQUEST.value()) {
            counterService.increment(REQUEST_ERROR_LABEL);
        }
        updateResponseTime(httpServletRequest, System.nanoTime() - start);
    }

    @Scheduled(fixedDelayString = "${monitoring.interval:10000}")
    public void reset() {
        counterService.reset(REQUEST_AMOUNT_LABEL);
        counterService.reset(REQUEST_ERROR_LABEL);
        averages.forEach((matcher, average) -> gaugeService.submit(REQUEST_AVERAGE_LABEL + matcher.getName(), average));
        counts.clear();
        averages.clear();
    }

    private void updateResponseTime(HttpServletRequest request, long duration) {
        logger.debug("Monitoring request {}", request.getServletPath());
        if (!CollectionUtils.isEmpty(monitoringProperties.getMatchers())) {
            monitoringProperties.getMatchers().stream()
                .filter(matcher -> matcher.isMatching(request))
                .forEach(matcher -> updateResponseTime(matcher, duration));
        }
    }

    private void updateResponseTime(MonitoringPathMatcher matcher, long duration) {
        int newCount = counts.getOrDefault(matcher, 0) + 1;
        long newAverage = ((averages.getOrDefault(matcher, 0L) * (newCount - 1)) + duration) / newCount;
        averages.put(matcher, newAverage);
        counts.put(matcher, newCount);
    }
}
