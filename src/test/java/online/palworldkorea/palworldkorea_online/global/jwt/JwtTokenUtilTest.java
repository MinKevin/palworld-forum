package online.palworldkorea.palworldkorea_online.global.jwt;

import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidTokenException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

class JwtTokenUtilTest {
//    @Autowired
//    JwtTokenUtil jwtTokenUtil;
//
//    @Test
//    void REFRESH_TOKEN_생성_후_유효성_검증_성공() {
//        // given
//        String email = "test@gmail.com";
//        List<GrantedAuthority> authorities = List.of()
//        // when
//        String generatedRefreshToken = jwtTokenUtil.generateRefreshToken(email);
//
//        // then
//        Assertions.assertThat(jwtTokenUtil.validateToken(generatedRefreshToken)).isTrue();
//    }
//
//    @Test
//    void TOKEN_유효성_검증_실패() {
//        // given
//        String email = "test@gmail.com";
//
//        // when
//        String generatedRefreshToken = jwtTokenUtil.generateRefreshToken(email);
//        String wrongGeneratedRefreshToken = generatedRefreshToken + "something";
//        // then
//        Assertions.assertThatThrownBy(() -> jwtTokenUtil.validateToken(wrongGeneratedRefreshToken))
//                .isInstanceOf(InvalidTokenException.class);
//     }
//
//     @Test
//     void 생성된_TOKEN에서_email정보_추출() {
//         // given
//         String email = "test@gmail.com";
//
//         // when
//         String generatedRefreshToken = jwtTokenUtil.generateRefreshToken(email);
//
//         // then
//         Assertions.assertThat(jwtTokenUtil.getEmailFromToken(generatedRefreshToken))
//                 .isEqualTo(email);
//      }
}