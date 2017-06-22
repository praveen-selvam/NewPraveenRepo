package com.autotrek.instantauto.web.filters;

import com.autotrek.instantauto.ApiException;
import com.autotrek.instantauto.ApiExceptionBuilder;
import com.autotrek.instantauto.services.AuthenticationService;
import com.autotrek.instantauto.services.TokenService;
import com.autotrek.instantauto.web.dto.ApiErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * This filter will perform token authentication.
 *
 * @author Joe C. McPherson
 */
@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationTokenFilter.class);

    @Value("${auth.token.header}")
    private String tokenHeader;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        LOGGER.debug(">>>> AuthenticationTokenFilter <<<<");
        String token = request.getHeader(tokenHeader);
        if (token != null) {
            LOGGER.debug("Request contains token. Doing token authentication.");
            String username = tokenService.getSubjectFromToken(token);
            if (username != null) {
                Authentication auth = authenticationService.getAuthentication(username);
                if(auth != null) {
                    SecurityContextHolder.getContext().setAuthentication(auth);
                    chain.doFilter(request, response);
                } else {
                    handleForbidden(request, response);
                }
            } else {
                handleForbidden(request, response);
            }
        } else {
            LOGGER.debug("Request does not contain a token. Passing along.");
            chain.doFilter(request, response);
        }
    }

    private void handleForbidden(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ApiException ex = ApiExceptionBuilder.FORBIDDEN.build();
        ApiErrorMessage msg = ApiErrorMessage.fromApiException(ex);
        response.setStatus(ex.getStatus().value());
        response.setHeader("Content-Type", "application/json");
        objectMapper.writeValue(response.getWriter(), msg);
    }
}
