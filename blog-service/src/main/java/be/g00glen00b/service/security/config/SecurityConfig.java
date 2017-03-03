package be.g00glen00b.service.security.config;

import be.g00glen00b.service.security.TokenAuthenticationFilter;
import be.g00glen00b.service.security.service.TokenAuthenticationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private TokenAuthenticationUserDetailsService service;
    private TokenAuthenticationFilter filter;

    @Autowired
    public SecurityConfig(TokenAuthenticationUserDetailsService service, TokenAuthenticationFilter filter) {
        this.service = service;
        this.filter = filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .antMatcher("/api/**")
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .addFilterBefore(filter, RequestHeaderAuthenticationFilter.class)
            .authenticationProvider(preAuthProvider())
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    public AuthenticationProvider preAuthProvider() {
        PreAuthenticatedAuthenticationProvider provider = new PreAuthenticatedAuthenticationProvider();
        provider.setPreAuthenticatedUserDetailsService(service);
        return provider;
    }
}
