package online.palworldkorea.palworldkorea_online.authentication.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
    private String refreshToken;
    private String accessToken;
}