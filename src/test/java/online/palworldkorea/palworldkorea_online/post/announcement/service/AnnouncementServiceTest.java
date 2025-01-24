package online.palworldkorea.palworldkorea_online.post.announcement.service;

import online.palworldkorea.palworldkorea_online.global.TestFixture;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.NotEnoughPermissionException;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.repository.MemberRepository;
import online.palworldkorea.palworldkorea_online.post.announcement.dto.AnnouncementDto;
import online.palworldkorea.palworldkorea_online.post.announcement.entity.Announcement;
import online.palworldkorea.palworldkorea_online.post.announcement.repository.AnnouncementRepository;
import online.palworldkorea.palworldkorea_online.post.common.SearchType;
import online.palworldkorea.palworldkorea_online.post.common.SortType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class AnnouncementServiceTest {
    @Autowired
    private AnnouncementService service;

    @Autowired
    private AnnouncementRepository repository;

    @Autowired
    private MemberRepository memberRepository;

    private Member testMember;
    private Announcement testPost;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        testMember = new Member("test@domain.com", "password", "testuser", null);
        memberRepository.save(testMember);

        testPost = new Announcement(testMember, "Test Title", "Test Content", null);
        repository.save(testPost);

        TestFixture.createData();
    }

    @Test
    void testGetPost() {
        // given

        // when
        AnnouncementDto.Response response = service.getPost(testPost.getId());

        // then
        Assertions.assertThat(response.getAuthor()).isEqualTo(testMember.getEmail());
        Assertions.assertThat(response.getTitle()).isEqualTo(testPost.getTitle());
        Assertions.assertThat(response.getContent()).isEqualTo(testPost.getContent());
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "z"})
    void testSearchPostsByTitle(String keyword) {
        // given
        int page = 1;
        int limit = 10;
        SortType sortType = SortType.RECENT;
        SearchType searchType = SearchType.TITLE;

        // when
        List<AnnouncementDto.Response> responses = service.searchPosts(searchType, page, limit, keyword, sortType);

        // then
        Assertions.assertThat(responses).isNotNull();
        responses.forEach(response -> {
            Assertions.assertThat(response.getTitle())
                    .contains(keyword);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "z"})
    void testSearchPostsByNickname(String keyword) {
        // given
        int page = 1;
        int limit = 10;
        SortType sortType = SortType.RECENT;
        SearchType searchType = SearchType.NICKNAME;
        // when
        List<AnnouncementDto.Response> responses = service.searchPosts(searchType, page, limit, keyword, sortType);

        // then
        Assertions.assertThat(responses).isNotNull();
        responses.forEach(response -> {
            Assertions.assertThat(response.getAuthor())
                    .contains(keyword);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"a", "z"})
    void testSearchPostsByContent(String keyword) {
        // given
        int page = 1;
        int limit = 10;
        SortType sortType = SortType.RECENT;
        SearchType searchType = SearchType.CONTENT;
        // when
        List<AnnouncementDto.Response> responses = service.searchPosts(searchType, page, limit, keyword, sortType);

        // then
        Assertions.assertThat(responses).isNotNull();
        responses.forEach(response -> {
            Assertions.assertThat(response.getContent())
                    .contains(keyword);
        });
    }

    @Test
    void testCreatePost() {
        try (MockedStatic<MemberUtil> mockedStatic = Mockito.mockStatic(MemberUtil.class)) {
            // given
            AnnouncementDto.Request requestDto = new AnnouncementDto.Request();
            requestDto.setTitle("New Title");
            requestDto.setContent("New Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn(testMember.getEmail());

            // when
            AnnouncementDto.Response response = service.createPost(requestDto);

            // then
            Assertions.assertThat(response.getAuthor()).isEqualTo(testMember.getEmail());
            Assertions.assertThat(response.getTitle()).isEqualTo(requestDto.getTitle());
            Assertions.assertThat(response.getContent()).isEqualTo(requestDto.getContent());
        }
    }

    @Test
    void testUpdatePost() {
        try (MockedStatic<MemberUtil> mockedStatic = Mockito.mockStatic(MemberUtil.class)) {
            // given
            AnnouncementDto.Request updateDto = new AnnouncementDto.Request();
            updateDto.setTitle("Updated Title");
            updateDto.setContent("Updated Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn(testMember.getEmail());

            // when
            AnnouncementDto.Response response = service.updatePost(testPost.getId(), updateDto);

            // then
            Assertions.assertThat(response.getAuthor()).isEqualTo(testMember.getEmail());
            Assertions.assertThat(response.getTitle()).isEqualTo(updateDto.getTitle());
            Assertions.assertThat(response.getContent()).isEqualTo(updateDto.getContent());
        }
    }

    @Test
    void testUpdatePost_NotEnoughPermission() {
        try (MockedStatic<MemberUtil> mockedStatic = Mockito.mockStatic(MemberUtil.class)) {
            // given
            AnnouncementDto.Request updateDto = new AnnouncementDto.Request();
            updateDto.setTitle("Updated Title");
            updateDto.setContent("Updated Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn("another@email.com");

            // when

            // then
            Assertions.assertThatThrownBy(
                    () -> service.updatePost(testPost.getId(), updateDto)
            ).isInstanceOf(NotEnoughPermissionException.class);
        }
    }

    @Test
    void testDeletePost() {
        try (MockedStatic<MemberUtil> mockedStatic = Mockito.mockStatic(MemberUtil.class)) {
            // given
            AnnouncementDto.Request requestDto = new AnnouncementDto.Request();
            requestDto.setTitle("New Title");
            requestDto.setContent("New Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn(testMember.getEmail());

            // when
            AnnouncementDto.Response newPostResponse = service.createPost(requestDto);
            AnnouncementDto.Response response = service.deletePost(newPostResponse.getId());

            // then
            Assertions.assertThat(response.getAuthor()).isEqualTo(testMember.getEmail());
            Assertions.assertThat(response.getTitle()).isEqualTo(requestDto.getTitle());
            Assertions.assertThat(response.getContent()).isEqualTo(requestDto.getContent());
            Assertions.assertThat(repository.findById(newPostResponse.getId())).isEmpty();
        }
    }

    @Test
    void testDeletePost_NotEnoughPermission() {
        try (MockedStatic<MemberUtil> mockedStatic = Mockito.mockStatic(MemberUtil.class)) {
            // given
            AnnouncementDto.Request requestDto = new AnnouncementDto.Request();
            requestDto.setTitle("New Title");
            requestDto.setContent("New Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn(testMember.getEmail());

            // when
            AnnouncementDto.Response newPostResponse = service.createPost(requestDto);
            mockedStatic.when(MemberUtil::getEmail).thenReturn("another@email.com");

            // then
            Assertions.assertThatThrownBy(() -> service.deletePost(newPostResponse.getId()))
                    .isInstanceOf(NotEnoughPermissionException.class);
        }
    }
}