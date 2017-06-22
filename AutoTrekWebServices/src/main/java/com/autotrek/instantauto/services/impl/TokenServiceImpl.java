package com.autotrek.instantauto.services.impl;

import com.autotrek.instantauto.services.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Joe C. McPherson
 */
@Service
public class TokenServiceImpl implements TokenService {

    @Value("${auth.token.secret:somesecret}")
    private String tokenSecret;
    @Value("${auth.token.issuer}")
    private String tokenIssuer;
    @Value("${auth.token.expire.in.seconds}")
    private Long tokenExpiresInSeconds;

    @Override
    public String generateToken(String username) {
        Date issuedAt = new Date();
        Date expiration = new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(tokenExpiresInSeconds));

        return Jwts.builder()
                .setIssuer(tokenIssuer)
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
    }

    @Override
    public String getSubjectFromToken(String token) {
        String ret = null;
        try {
            // This will handle the signature and expiration.
            Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
            if (claims.getIssuer().equals(tokenIssuer)) {
                ret = claims.getSubject();
            }
        } catch (ExpiredJwtException exp) {
            // Expired
            ret = null;
        } catch (Exception ex) {
            // NO OP
            ret = null;
        }

        return ret;
    }

}
