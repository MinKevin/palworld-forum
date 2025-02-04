package online.palworldkorea.palworldkorea_online.post.comment.repository;

import online.palworldkorea.palworldkorea_online.post.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.concurrent.atomic.AtomicLong;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Long countById(long id);
}
