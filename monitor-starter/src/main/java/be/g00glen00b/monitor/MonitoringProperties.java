package be.g00glen00b.monitor;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("monitoring")
public class MonitoringProperties {
    private String path = "/prometheus";
    private int interval = 10;
    private List<MonitoringPathMatcher> matchers;

    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public int getInterval() {
        return interval;
    }
    public void setInterval(int interval) {
        this.interval = interval;
    }

    public List<MonitoringPathMatcher> getMatchers() {
        return matchers;
    }
    public void setMatchers(List<MonitoringPathMatcher> matchers) {
        this.matchers = matchers;
    }
}
