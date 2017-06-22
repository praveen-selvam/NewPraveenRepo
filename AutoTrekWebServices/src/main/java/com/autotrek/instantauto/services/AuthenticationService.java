package com.autotrek.instantauto.services;

import org.springframework.security.core.Authentication;

/**
 *
 * @author Joe C. McPherson
 */
public interface AuthenticationService {

    /**
     * Authenticate user credentials.
     *
     * @param username
     * @param password
     * @return
     */
    public boolean validCredentials(String username, String password);

    /**
     * Returns an Authentication object for the provide username. If the user
     * is not found then null is returned.
     * 
     * @param username
     * @return
     */
    public Authentication getAuthentication(String username);
}
