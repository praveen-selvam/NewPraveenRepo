package com.autotrek.instantauto.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.autotrek.instantauto.web.dto.ApiErrorMessage;
import com.autotrek.instantauto.ApiException;
import com.autotrek.instantauto.ApiExceptionBuilder;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * There is no entry point for the API. So any Spring Security unauthenticated
 * request will come here. We will simply return a standard baked message.
 *
 * @author Joe C. McPherson
 */
@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ApiException ex = ApiExceptionBuilder.FORBIDDEN.build(authException);
        ApiErrorMessage msg = ApiErrorMessage.fromApiException(ex);
        response.setStatus(ex.getStatus().value());
        response.setHeader("Content-Type", "application/json");

        objectMapper.writeValue(response.getWriter(), msg);
    }

}
