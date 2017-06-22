package com.autotrek.instantauto.services.impl;

import com.autotrek.instantauto.domain.User;
import com.autotrek.instantauto.services.AuthenticationService;
import com.autotrek.instantauto.services.UserService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Joe C. McPherson
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean validCredentials(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        } else {
            return false;
        }
    }

    @Override
    public Authentication getAuthentication(String username) {
        Authentication ret = null;
        User user = userService.getUserByUsername(username);

        if(user != null) {
            // TODO need to set the SITE_ADMIN based on GLOBAL ADMIN or not.
            ret = new UsernamePasswordAuthenticationToken(user, null, Arrays.asList(new SimpleGrantedAuthority("SITE_ADMIN")));
        }

        return ret;
    }

}
