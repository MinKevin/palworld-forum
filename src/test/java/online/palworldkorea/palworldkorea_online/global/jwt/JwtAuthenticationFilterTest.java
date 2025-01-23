package online.palworldkorea.palworldkorea_online.global.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {
    private MockMvc mockMvc;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @Mock
    private FilterChain filterChain;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void doFilterInternal_ValidToken_SuccessfulAuthentication() throws ServletException, IOException {
        // given
        String validToken = "valid.jwt.token";
        String email = "test@example.com";
        List<GrantedAuthority> authorities = Arrays.asList(MemberRole.USER_LEVEL0);

        when(request.getHeader("Authorization")).thenReturn("Bearer " + validToken);
        when(jwtTokenUtil.getEmailFromToken(validToken)).thenReturn(email);
        when(jwtTokenUtil.getAuthoritiesFromToken(validToken)).thenReturn(authorities);
        doNothing().when(jwtTokenUtil).validateToken(validToken);

        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);

        // when
        jwtAuthenticationFilter.doFilterInternal(request, response, filterChain);

        // then
        verify(jwtTokenUtil).validateToken(validToken);
     }

    @Test
    void shoudSetAuthenticationInSecurityContext_whenTokenIsValid() {
        // given
        String validToken = "valid.jwt.token";
        String email = "valid@example.com";
        WebAuthenticationDetails details = new WebAuthenticationDetails(mock(HttpServletRequest.class));

        // when

        // then
        Assertions.assertThat(jwtTokenUtil.getEmailFromToken(validToken)).isEqualTo(email);
        Assertions.assertThat(jwtTokenUtil.getAuthoritiesFromToken(validToken)).isEqualTo(List.of(MemberRole.USER_LEVEL0));
     }
}
