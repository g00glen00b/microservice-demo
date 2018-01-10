package be.g00glen00b.monitor;

import java.util.Collection;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.spring.boot.SpringBootMetricsCollector;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.actuate.endpoint.PublicMetrics;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(MetricRepositoryAutoConfiguration.class)
@ConditionalOnWebApplication
@ConditionalOnClass(name = {"org.springframework.boot.actuate.metrics.CounterService", "org.springframework.boot.actuate.metrics.GaugeService"})
@EnableConfigurationProperties(MonitoringProperties.class)
public class MonitorAutoConfiguration {
    @Bean
    public SpringBootMetricsCollector springBootMetricsCollector(Collection<PublicMetrics> publicMetrics) {
        SpringBootMetricsCollector springBootMetricsCollector = new SpringBootMetricsCollector(publicMetrics);
        springBootMetricsCollector.register();
        return springBootMetricsCollector;
    }

    @Bean
    public ServletRegistrationBean servletRegistrationBean(MonitoringProperties properties) {
        DefaultExports.initialize();
        return new ServletRegistrationBean(new MetricsServlet(), properties.getPath());
    }

    @Bean
    public MonitoringFilter filter(CounterService counterService, GaugeService gaugeService, MonitoringProperties monitoringProperties) {
        return new MonitoringFilter(counterService, gaugeService, monitoringProperties);
    }
}
