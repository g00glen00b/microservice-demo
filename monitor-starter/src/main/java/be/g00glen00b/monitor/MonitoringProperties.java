package be.g00glen00b.monitor;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("monitoring")
public class MonitoringProperties {
    private String path = "/prometheus";
    private int interval = 10;

    public MonitoringProperties() {
    }

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
}
