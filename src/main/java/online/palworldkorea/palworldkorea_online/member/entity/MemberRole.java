package online.palworldkorea.palworldkorea_online.member.entity;

import org.springframework.security.core.GrantedAuthority;

public enum MemberRole implements GrantedAuthority {
    USER_LEVEL0, USER_LEVEL1, USER_LEVEL2, USER_LEVEL3, PARTNER, ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
