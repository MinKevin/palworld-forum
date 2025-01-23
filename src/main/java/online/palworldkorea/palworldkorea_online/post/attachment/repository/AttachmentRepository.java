package online.palworldkorea.palworldkorea_online.post.attachment.repository;

import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
