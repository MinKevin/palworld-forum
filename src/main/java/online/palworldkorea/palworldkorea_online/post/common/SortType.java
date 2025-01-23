package online.palworldkorea.palworldkorea_online.post.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SortType {
    HITS("hits"),
    RECENT("createdAt"),
    ;

    private final String columnName;
}
