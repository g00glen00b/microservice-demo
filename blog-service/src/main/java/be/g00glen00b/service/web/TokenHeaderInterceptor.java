package be.g00glen00b.service.web;

import java.io.IOException;
import be.g00glen00b.service.security.model.TokenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class TokenHeaderInterceptor implements ClientHttpRequestInterceptor {
    private TokenProperties properties;

    @Autowired
    public TokenHeaderInterceptor(TokenProperties properties) {
        this.properties = properties;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] bytes, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        return clientHttpRequestExecution.execute(httpRequest, bytes);
    }
}
