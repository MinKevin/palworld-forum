package online.palworldkorea.palworldkorea_online.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidAccessTokenException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("Authorization") != null) {
            String token = getJwtFromRequest(request);

            jwtTokenUtil.validateToken(token);

            String email = jwtTokenUtil.getEmailFromToken(token);
            List<GrantedAuthority> authorities = jwtTokenUtil.getAuthoritiesFromToken(token);
            UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(email, authorities);

            WebAuthenticationDetails details = new WebAuthenticationDetails(request);
            authenticationToken.setDetails(details);

            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        }

        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer "))
            return bearerToken.substring(7);

        throw new InvalidAccessTokenException();
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(String email, List<GrantedAuthority> authorities) {
        return new UsernamePasswordAuthenticationToken(
                email,
                null,
                authorities
        );
    }
}
