package online.palworldkorea.palworldkorea_online.post.comment.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidCommentIdException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidPostIdException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.NotEnoughPermissionException;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.post.comment.dto.CommentDto;
import online.palworldkorea.palworldkorea_online.post.comment.entity.Comment;
import online.palworldkorea.palworldkorea_online.post.comment.mapper.CommentMapper;
import online.palworldkorea.palworldkorea_online.post.comment.repository.CommentRepository;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.common.repository.CommonPostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommonPostRepository<CommonPost> commonPostRepository;
    private final CommentMapper commentMapper;

    public CommentDto.Response createComment(Long postId, CommentDto.Request commentRequestDto) {
        CommonPost post = getCommonPostById(postId);
        Comment comment = new Comment(post.getAuthor(), post, commentRequestDto.getContent(), null);
        post.increateCountOfComments();
        commentRepository.save(comment);
        return commentMapper.toResponse(comment);
    }

    public CommentDto.Response updateComment(Long commentId, CommentDto.Request commentRequestDto) {
        Comment comment = getCommentById(commentId);

        validAuthority(comment);

        comment.updateContent(commentRequestDto.getContent());

        commentRepository.save(comment);

        return commentMapper.toResponse(comment);
    }

    public boolean deleteComment(Long postId, Long commentId) {
        CommonPost post = getCommonPostById(postId);

        Comment comment = getCommentById(commentId);

        validAuthority(comment);

        commentRepository.delete(comment);
        post.decreaseCountOfComments();

        return true;
    }

    private void validAuthority(Comment comment) {
        if (!comment.getAuthor().getEmail().equals(MemberUtil.getEmail()))
            throw new NotEnoughPermissionException();
    }

    private CommonPost getCommonPostById(Long postId) {
        return commonPostRepository.findById(postId)
                .orElseThrow(InvalidPostIdException::new);
    }

    private Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(InvalidCommentIdException::new);
    }
}
