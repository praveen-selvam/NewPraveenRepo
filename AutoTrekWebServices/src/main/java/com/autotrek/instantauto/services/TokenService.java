package com.autotrek.instantauto.services;

/**
 * This is the token service.
 *
 * @author Joe C. McPherson
 */
public interface TokenService {

    /**
     * This will generate a new token for the provided user.
     *
     * @param username
     * @return
     */
    public String generateToken(String username);

    /**
     * This will validate a token. If the token is valid a username will be
     * returned. If the token is not valid then null will be returned.
     *
     * @param token
     * @return
     */
    public String getSubjectFromToken(String token);
}
