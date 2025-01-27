package online.palworldkorea.palworldkorea_online.admin.link.repository;

import online.palworldkorea.palworldkorea_online.admin.LinkType;
import online.palworldkorea.palworldkorea_online.admin.link.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
    Optional<Link> findByLinkType(LinkType linkType);
}
