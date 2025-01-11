package online.palworldkorea.palworldkorea_online.global.jwt;

import jakarta.servlet.http.HttpServletRequest;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JwtAuthenticationFilterTest {
    private MockMvc mockMvc;

    @Mock
    private JwtTokenUtil jwtTokenUtil;

    @InjectMocks
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(jwtAuthenticationFilter).build();
    }

    @Test
    void shoudSetAuthenticationInSecurityContext_whenTokenIsValid() throws Exception {
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