package online.palworldkorea.palworldkorea_online.post.common.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidPostIdException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.NotEnoughPermissionException;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import online.palworldkorea.palworldkorea_online.post.common.SearchType;
import online.palworldkorea.palworldkorea_online.post.common.SortType;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import online.palworldkorea.palworldkorea_online.post.common.repository.CommonPostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
public abstract class CommonPostService<
        E extends CommonPost,
        Q extends CommonPostDto.Request,
        P extends CommonPostDto.Response,
        M extends CommonPostMapper<E, Q, P>> {
    protected final MemberService memberService;
    private final AttachmentService attachmentService;
    private final CommonPostRepository<E> postRepository;
    private final M postMapper;

    private final Class<E> entityClass;

    public P getPost(long id) {
        E post = getPostById(id);

        post.increaseHit();

        return postMapper.toResponse(post);
    }

    public List<P> searchPosts(SearchType searchType, int page, int limit, String keyword, SortType sortType) {
        Pageable pageable = PageRequest.of(page - 1, limit, Sort.by(sortType.getColumnName()).descending());
        String dtype = getDtype();

        List<E> postList = List.of();
        switch (searchType) {
            case NICKNAME: {
                postList = getPostListByNickname(keyword, dtype, pageable);
                break;
            }
            case TITLE: {
                postList = getPostListByTitle(keyword, dtype, pageable);
                break;
            }
            case CONTENT: {
                postList = getPostListByContent(keyword, dtype, pageable);
                break;
            }
        }

        return postList.stream()
                .map(postMapper::toResponseWithoutComments)
                .toList();
    }

    public P createPost(Q postRequestDto) {
        Member author = memberService.getMemberByEmail(MemberUtil.getEmail());

        E post = (E)postRequestDto.toEntity(author);

        postRepository.save(post);

        return postMapper.toResponse(post);
    }

    public P updatePost(long id, Q postRequestDto) {
        E post = getPostById(id);

        hasEditPermission(post);

        post.update(postRequestDto);

        postRepository.save(post);

        return postMapper.toResponse(post);
    }

    public P deletePost(long id) {
        E post = getPostById(id);

        hasEditPermission(post);

        postRepository.delete(post);

        return postMapper.toResponse(post);
    }

    private List<E> getPostListByContent(String keyword, String dtype, Pageable pageable) {
        return postRepository.findByContentContainingIgnoreCase(keyword, pageable).getContent();
    }

    private List<E> getPostListByNickname(String keyword, String dtype, Pageable pageable) {
        return postRepository.findByAuthor_NicknameContainingIgnoreCase(keyword, pageable).getContent();
    }

    private List<E> getPostListByTitle(String keyword, String dtype, Pageable pageable) {
        return postRepository.findByTitleContainingIgnoreCase(keyword, pageable).getContent();
    }

    private E getPostById(long id) {
        return postRepository.findById(id)
                .orElseThrow(InvalidPostIdException::new);
    }

    private String getDtype() {
        return entityClass.getSimpleName().replaceFirst("Post", "").toLowerCase();
    }

    private void hasEditPermission(E post) {
        if (!post.getAuthor().getEmail().equals(MemberUtil.getEmail()) && memberService.getMemberByEmail(MemberUtil.getEmail()).getMemberRole() != MemberRole.ADMIN)
            throw new NotEnoughPermissionException();
    }
}
