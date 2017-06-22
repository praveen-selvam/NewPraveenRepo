package com.autotrek.instantauto.config;

import com.autotrek.instantauto.web.ApiAuthenticationEntryPoint;
import com.autotrek.instantauto.web.filters.AuthenticationTokenFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.ExceptionTranslationFilter;

/**
 * Configuration class for Web security.
 *
 * @author Joe C. McPherson
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    private ApiAuthenticationEntryPoint unauthorizedEntryPoint;

    @Autowired
    private AuthenticationTokenFilter authenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // No need for CSRF
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint).and()
                // No session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // Allow anonymous access to get health
                .antMatchers(HttpMethod.GET, "/site/health").permitAll()
                // Allow anonymous access for OPTIONS
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // Allow anonymous access to POST to /auth/token.
                .antMatchers(HttpMethod.POST, "/auth/token").permitAll()
                // Anything else will be authenticated
                .anyRequest().authenticated();

        http.addFilterAfter(authenticationTokenFilter, ExceptionTranslationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
