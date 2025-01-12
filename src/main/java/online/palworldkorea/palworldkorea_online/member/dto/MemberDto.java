package online.palworldkorea.palworldkorea_online.member.dto;

import jakarta.validation.constraints.Pattern;
import lombok.*;
import online.palworldkorea.palworldkorea_online.authentication.dto.TokenDto;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MemberDto {
    private MemberDto() {}

    @Getter
    @Setter
    public static class RegisterReguest {
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-={}|\\[\\]:'\";<>?,./]).{8,16}$", message = "비밀번호는 8자 이상 16자 이하, 특수문자를 포함해야 합니다.")
        private String password;

        @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,10}$", message = "닉네임은 특수문자를 제외한 2~10자리여야 합니다.")
        private String nickname;

        private MemberRole memberRole = MemberRole.USER_LEVEL0;

        public Member toEntity(PasswordEncoder passwordEncoder) {
            return new Member(email, getEncodedPassword(passwordEncoder), nickname, memberRole);
        }

        private String getEncodedPassword(PasswordEncoder passwordEncoder) {
            return passwordEncoder.encode(password);
        }
    }

    @Getter
    @Setter
    public static class LoginRequest {
        @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @Pattern(regexp = "^(?=.*[!@#$%^&*()_+\\-={}|\\[\\]:'\";<>?,./]).{8,16}$", message = "비밀번호는 8자 이상 16자 이하, 특수문자를 포함해야 합니다.")
        private String password;
    }

    @Getter
    @Setter
    public static class Response {
        private String email;
        private String nickname;
        private MemberRole memberRole;
        private TokenDto token;
    }
}
