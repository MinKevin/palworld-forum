package online.palworldkorea.palworldkorea_online.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.accessTokenExpirationTime}")
    private long accessTokenExpirationTime;

    @Value("${jwt.refreshTokenExpirationTime}")
    private long refreshTokenExpirationTime;

    public String generateAccessToken(String email, List<GrantedAuthority> authorities) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + accessTokenExpirationTime);
        Key key = getKey();

        return generateNewToken(now, email, authorities, expiration, key);
    }

    public String generateRefreshToken(String email, List<GrantedAuthority> authorities) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + refreshTokenExpirationTime);

        Key key = getKey();

        return generateNewToken(now, email, authorities, expiration, key);
    }

    public boolean validateToken(String token) {
        SecretKey key = getKey();

        Date expiration = getPayload(key, token).getExpiration();
        if (expiration.before(new Date()))
            throw new InvalidTokenException();

        return true;
    }

    public String getEmailFromToken(String token) {
        SecretKey key = getKey();

        return getPayload(key, token).getSubject();
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        SecretKey key = getKey();

        return getPayload(key, token).get("authorities", List.class);
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String generateNewToken(Date now, String email, List<GrantedAuthority> authorities, Date expiration, Key key) {
        return Jwts.builder()
                .issuedAt(now)
                .subject(email)
                .claim("authorities", authorities)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    private Claims getPayload(SecretKey key, String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }
}
