package online.palworldkorea.palworldkorea_online.authentication.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TokenDto {
    private final String refreshToken;
    private final String accessToken;
}