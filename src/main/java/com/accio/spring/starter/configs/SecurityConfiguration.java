package com.accio.spring.starter.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * For configuration settings.
     *
     * @param http HttpSecurity instance
     * @throws Exception any exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        /*
         * http.headers()
         * .contentSecurityPolicy(
         *  "script-src 'self' https://trustedscripts.example.com;
         *  object-src https://trustedplugins.example.com;
         *  report-uri /csp-report-endpoint/"
         * );
         */
        // https://developer.mozilla.org/en-US/docs/Web/HTTP/CSP
        // disable csrf for api
        http.cors().and().csrf().disable();
        /*
         * http .cors() .and() .headers()
         * .contentSecurityPolicy("script-src 'self' default-src 'self'");
         */
    }

}
