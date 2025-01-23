package online.palworldkorea.palworldkorea_online.post.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchType {
    NICKNAME("nickname"),
    TITLE("title"),
    CONTENT("content"),
    ;

    private final String columnName;
}
