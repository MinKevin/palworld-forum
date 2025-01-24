package online.palworldkorea.palworldkorea_online.post.announcement.repository;

import online.palworldkorea.palworldkorea_online.post.announcement.entity.Announcement;
import online.palworldkorea.palworldkorea_online.post.common.repository.CommonPostRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepository extends CommonPostRepository<Announcement> {
}
