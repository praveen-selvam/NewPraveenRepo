package com.autotrek.instantauto.web.controllers;

import com.autotrek.instantauto.ApiExceptionBuilder;
import com.autotrek.instantauto.services.AuthenticationService;
import com.autotrek.instantauto.services.TokenService;
import com.autotrek.instantauto.web.dto.AuthenticationDto;
import com.autotrek.instantauto.web.dto.UserCredentialsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Contains the authentication endpoints.
 *
 * @author Joe C. McPherson
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @RequestMapping(value = "token", method = RequestMethod.POST)
    public AuthenticationDto generateToken(@RequestBody UserCredentialsDto userCredentials) {
        validateUserCredentialsDto(userCredentials);
        if (authenticationService.validCredentials(userCredentials.username, userCredentials.password)) {
            String token = tokenService.generateToken(userCredentials.username);
            AuthenticationDto dto = new AuthenticationDto();
            dto.token = token;

            return dto;
        }

        throw ApiExceptionBuilder.UNAUTHORIZED.build();
    }

    private void validateUserCredentialsDto(UserCredentialsDto userCredentials) {
        if (userCredentials != null) {
            if (StringUtils.isEmpty(userCredentials.username) || StringUtils.isEmpty(userCredentials.password)) {
                throw ApiExceptionBuilder.BAD_REQUEST.build();
            }
        } else {
            throw ApiExceptionBuilder.BAD_REQUEST.build();
        }
    }
}
