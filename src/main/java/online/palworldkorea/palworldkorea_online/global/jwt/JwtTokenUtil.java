package online.palworldkorea.palworldkorea_online.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.accessTokenExpirationTime}")
    private long accessTokenExpirationTime;

    @Value("${jwt.refreshTokenExpirationTime}")
    private long refreshTokenExpirationTime;

    public String generateAccessToken( String email, List<GrantedAuthority> authorities) {
        Date expiration = new Date(new Date().getTime() + accessTokenExpirationTime);
        SecretKey key = getKey();

        return generateNewToken(email, authorities, expiration, key);
    }

    public String generateRefreshToken(String email, List<GrantedAuthority> authorities) {
        Date expiration = new Date(new Date().getTime() + refreshTokenExpirationTime);

        SecretKey key = getKey();

        return generateNewToken(email, authorities, expiration, key);
    }

    public boolean validateToken(String token) {
        SecretKey key = getKey();

        Date expiration = getPayload(key, token).getExpiration();
        if (expiration.before(new Date(System.currentTimeMillis())))
            throw new InvalidTokenException();

        return true;
    }

    public String getEmailFromToken(String token) {
        SecretKey key = getKey();

        return getPayload(key, token).getSubject();
    }

    public List<GrantedAuthority> getAuthoritiesFromToken(String token) {
        SecretKey key = getKey();
        List<String> authoritiesList = getPayload(key, token).get("authorities", List.class);

        return authoritiesList.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    private SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    private String generateNewToken(String email, List<GrantedAuthority> authorities, Date expiration, SecretKey key) {
        List<String> authoritiesStrings = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .subject(email)
                .claim("authorities", authoritiesStrings)
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
            System.out.println(e.getMessage());
            throw new InvalidTokenException();
        }
    }
}
