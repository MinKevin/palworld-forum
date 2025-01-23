package online.palworldkorea.palworldkorea_online.post.common.service;

import online.palworldkorea.palworldkorea_online.global.TestFixture;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.NotEnoughPermissionException;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.repository.MemberRepository;
import online.palworldkorea.palworldkorea_online.post.common.SearchType;
import online.palworldkorea.palworldkorea_online.post.common.SortType;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.common.repository.CommonPostRepository;
import online.palworldkorea.palworldkorea_online.post.unified.mapper.UnifiedPostMapper;
import online.palworldkorea.palworldkorea_online.post.unified.service.UnifiedPostService;
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
class CommonPostServiceTest {
    @Autowired
    private UnifiedPostService unifiedPostService;

    @Autowired
    private CommonPostRepository<CommonPost> postRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private UnifiedPostMapper unifiedPostMapper;

    private Member testMember;
    private CommonPost testPost;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        testMember = new Member("test@domain.com", "password", "testuser", null);
        memberRepository.save(testMember);

        testPost = new CommonPost(testMember, "Test Title", "Test Content", null);
        postRepository.save(testPost);

        TestFixture.createData();
    }

    @Test
    void testGetPost() {
        // given

        // when
        CommonPostDto.Response response = unifiedPostService.getPost(testPost.getId());

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
        List<CommonPostDto.Response> responses = unifiedPostService.searchPosts(searchType, page, limit, keyword, sortType);

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
        List<CommonPostDto.Response> responses = unifiedPostService.searchPosts(searchType, page, limit, keyword, sortType);

        // then
        Assertions.assertThat(responses).isNotNull();
        responses.forEach(response -> {
            Assertions.assertThat(response.getAuthor())
                    .contains(keyword);

//            Assertions.assertThat(response.getDtype())
//                    .isEqualTo(CommonPost.class.getSimpleName()
//                                    .replaceFirst("Post", "").toLowerCase());
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
        List<CommonPostDto.Response> responses = unifiedPostService.searchPosts(searchType, page, limit, keyword, sortType);

        // then
        Assertions.assertThat(responses).isNotNull();
        responses.forEach(response -> {
            Assertions.assertThat(response.getContent())
                    .contains(keyword);

//            Assertions.assertThat(response.getDtype())
//                    .isEqualTo(CommonPost.class.getSimpleName()
//                                    .replaceFirst("Post", "").toLowerCase());
        });
    }

    @Test
    void testCreatePost() {
        try (MockedStatic<MemberUtil> mockedStatic = Mockito.mockStatic(MemberUtil.class)) {
            // given
            CommonPostDto.Request requestDto = new CommonPostDto.Request();
            requestDto.setTitle("New Title");
            requestDto.setContent("New Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn(testMember.getEmail());

            // when
            CommonPostDto.Response response = unifiedPostService.createPost(requestDto);

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
            CommonPostDto.Request updateDto = new CommonPostDto.Request();
            updateDto.setTitle("Updated Title");
            updateDto.setContent("Updated Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn(testMember.getEmail());

            // when
            CommonPostDto.Response response = unifiedPostService.updatePost(testPost.getId(), updateDto);

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
            CommonPostDto.Request updateDto = new CommonPostDto.Request();
            updateDto.setTitle("Updated Title");
            updateDto.setContent("Updated Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn("another@email.com");

            // when

            // then
            Assertions.assertThatThrownBy(
                    () -> unifiedPostService.updatePost(testPost.getId(), updateDto)
            ).isInstanceOf(NotEnoughPermissionException.class);
        }
    }

    @Test
    void testDeletePost() {
        try (MockedStatic<MemberUtil> mockedStatic = Mockito.mockStatic(MemberUtil.class)) {
            // given
            CommonPostDto.Request requestDto = new CommonPostDto.Request();
            requestDto.setTitle("New Title");
            requestDto.setContent("New Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn(testMember.getEmail());

            // when
            CommonPostDto.Response newPostResponse = unifiedPostService.createPost(requestDto);
            CommonPostDto.Response response = unifiedPostService.deletePost(newPostResponse.getId());

            // then
            Assertions.assertThat(response.getAuthor()).isEqualTo(testMember.getEmail());
            Assertions.assertThat(response.getTitle()).isEqualTo(requestDto.getTitle());
            Assertions.assertThat(response.getContent()).isEqualTo(requestDto.getContent());
            Assertions.assertThat(postRepository.findById(newPostResponse.getId())).isEmpty();
        }
    }

    @Test
    void testDeletePost_NotEnoughPermission() {
        try (MockedStatic<MemberUtil> mockedStatic = Mockito.mockStatic(MemberUtil.class)) {
            // given
            CommonPostDto.Request requestDto = new CommonPostDto.Request();
            requestDto.setTitle("New Title");
            requestDto.setContent("New Content");
            mockedStatic.when(MemberUtil::getEmail).thenReturn(testMember.getEmail());

            // when
            CommonPostDto.Response newPostResponse = unifiedPostService.createPost(requestDto);
            mockedStatic.when(MemberUtil::getEmail).thenReturn("another@email.com");

            // then
            Assertions.assertThatThrownBy(() -> unifiedPostService.deletePost(newPostResponse.getId()))
                    .isInstanceOf(NotEnoughPermissionException.class);
        }
    }
}