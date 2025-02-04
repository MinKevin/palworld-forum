package online.palworldkorea.palworldkorea_online.member.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Getter
@RequiredArgsConstructor
public enum MemberRole implements GrantedAuthority {
    ADMIN("운영자", 0),
    PARTNER("파트너", 0),

    LEGENDARY("전설", 700),
    HEROIC("영웅", 360),
    RARE("희귀", 180),
    EXTRA_ORDINARY("비범", 70),
    NORMAL("일반", 0);


    private final String korean;
    private final int criteria;

    @Override
    public String getAuthority() {
        return name();
    }
}
