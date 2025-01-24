package online.palworldkorea.palworldkorea_online.post.common.repository;

import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommonPostRepository<E extends CommonPost> extends JpaRepository<E, Long> {
    Page<E> findByAuthor_NicknameContainingIgnoreCase(String keyword, Pageable pageable);

    Page<E> findByTitleContainingIgnoreCase(String keyword, Pageable pageable);

    Page<E> findByContentContainingIgnoreCase(String keyword, Pageable pageable);
}
