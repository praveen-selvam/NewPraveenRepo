package com.autotrek.instantauto.web.util;

import com.autotrek.instantauto.ApiExceptionBuilder;
import com.autotrek.instantauto.domain.User;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * This is a helper class for the Spring Security Context.
 *
 * @author Joe C. McPherson
 */
@Component
public class SecurityContextHelper {

    /**
     * This method will return the calling user from the Spring Security
     * SecurityContextHolder object. It does not return null, but it throws
     * an API Exception if the user is not found. This is intended for Controllers
     * to use as a safe-guard against something silly going on. If you want do not
     * want an exception thrown, then @see #getCallingUserNoException();
     */
    public User getCallingUser() {
        User ret = getCallingUserNoException();

        if (ret != null) {
            return ret;
        } else {
            throw ApiExceptionBuilder.UNAUTHORIZED.build();
        }
    }

    /**
     * This differs from #getCallingUser in that it will not throw an exception
     * but return null if the user is not found in the SpringSecurityContextHolder.
     */
    public User getCallingUserNoException() {
        User ret = null;
        SecurityContext context = SecurityContextHolder.getContext();

        if (context != null &&
                context.getAuthentication() != null &&
                context.getAuthentication().getPrincipal() != null) {
            ret = (User) context.getAuthentication().getPrincipal();
        }

        return ret;
    }
}
