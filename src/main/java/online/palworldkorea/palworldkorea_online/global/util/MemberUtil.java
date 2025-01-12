package online.palworldkorea.palworldkorea_online.global.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class MemberUtil {
    private MemberUtil() {}

    public static String getEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
