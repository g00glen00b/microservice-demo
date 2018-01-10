package be.g00glen00b.monitor;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.util.AntPathMatcher;

public class MonitoringPathMatcher {
    private static final AntPathMatcher MATCHER = new AntPathMatcher();
    private String name;
    private HttpMethod method;
    private String matcher;

    public MonitoringPathMatcher() {
    }

    public MonitoringPathMatcher(String name, HttpMethod method, String matcher) {
        this.name = name;
        this.method = method;
        this.matcher = matcher;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public HttpMethod getMethod() {
        return method;
    }
    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getMatcher() {
        return matcher;
    }
    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public boolean isMatching(HttpServletRequest request) {
        return getMethod().matches(request.getMethod()) && MATCHER.match(getMatcher(), request.getServletPath());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof MonitoringPathMatcher)) {
            return false;
        } else {
            MonitoringPathMatcher that = (MonitoringPathMatcher) o;
            return new EqualsBuilder()
                .append(getName(), that.getName())
                .isEquals();
        }
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(getName())
            .toHashCode();
    }
}
