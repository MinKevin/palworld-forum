package online.palworldkorea.palworldkorea_online.post.announcement.repository;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.member.repository.MemberRepository;
import online.palworldkorea.palworldkorea_online.post.announcement.entity.Announcement;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
class AnnouncementRepositoryTest {
    @Autowired
    private AnnouncementRepository announcementRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        Member member = new Member("test@gmail.com", "pwd", "nick", MemberRole.USER_LEVEL0);
        memberRepository.save(member);
        Announcement announcement = new Announcement(member, "title", "content", null);
        announcementRepository.save(announcement);
    }

    @ParameterizedTest
    @ValueSource(strings = {"t", ""})
    void testFindByTitleContainingIgnoreCase(String keyword) {
        // given
        String dtype = "announcement";

        // when
        Page<Announcement> result = announcementRepository
                .findByTitleContainingIgnoreCase(keyword, PageRequest.of(0, 10));

        // then
        Assertions.assertThat(result.getContent().size()).isNotZero();
        result.getContent()
                .forEach(post -> {
                    Assertions.assertThat(post.getTitle().toLowerCase()).contains(keyword);
                });
    }
}