package online.palworldkorea.palworldkorea_online.post.unified.mapper;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.post.comment.dto.CommentDto;
import online.palworldkorea.palworldkorea_online.post.comment.entity.Comment;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UnifiedPostMapperTest {
    private final UnifiedPostMapper unifiedPostMapper = Mappers.getMapper(UnifiedPostMapper.class);

    private String email = "author@example.com";
    private String password = "password";
    private String nickname = "authorNickname";
    private MemberRole memberRole = MemberRole.NORMAL;

    private String title = "Post Title";
    private String content = "Post Content";
    private List<MultipartFile> attachments = null;

    @Test
    void testToResponse() {
        // given
        Member author = new Member(email, password, nickname, memberRole);

        CommonPostDto.Request postRequestDto = new CommonPostDto.Request();
        postRequestDto.setTitle(title);
        postRequestDto.setContent(content);
        postRequestDto.setAttachments(attachments);

        CommonPost post = postRequestDto.toEntity(author, null);

        // when
        CommonPostDto.Response postResponseDto = unifiedPostMapper.toResponse(post);

        // then
        Assertions.assertThat(postResponseDto).isNotNull();
        Assertions.assertThat(postResponseDto.getAuthor()).isEqualTo(author.getEmail());
        Assertions.assertThat(postResponseDto.getTitle()).isEqualTo(title);
        Assertions.assertThat(postResponseDto.getContent()).isEqualTo(content);
        Assertions.assertThat(postResponseDto.getHits()).isEqualTo(post.getHits());
        Assertions.assertThat(postResponseDto.getCountOfComments()).isEqualTo(post.getCountOfComments());
        Assertions.assertThat(postResponseDto.getAttachments()).isEqualTo(attachments);
    }

    @Test
    void testToResponseWithoutComments() {
        // given
        Member author = new Member(email, password, nickname, memberRole);

        CommonPostDto.Request postRequestDto = new CommonPostDto.Request();
        postRequestDto.setTitle(title);
        postRequestDto.setContent(content);
        postRequestDto.setAttachments(attachments);

        CommonPost post = postRequestDto.toEntity(author, null);

        List<Comment> comments = new ArrayList<>();
        Comment parent = null;
        for (int i = 0; i < 10; i++) {
            CommentDto.Request commentRequestDto = new CommentDto.Request();
            commentRequestDto.setContent("comment conent : " + i);

            Comment comment;
            if (parent == null) {
                comment = commentRequestDto.toEntity(author, post, null);
                parent = comment;
                comments.add(comment);
            } else {
                comment = commentRequestDto.toEntity(author, post, parent);
                parent = null;
            }
        }
        // when


        // then

    }
}