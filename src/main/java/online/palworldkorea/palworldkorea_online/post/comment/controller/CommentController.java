package online.palworldkorea.palworldkorea_online.post.comment.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import online.palworldkorea.palworldkorea_online.post.comment.dto.CommentDto;
import online.palworldkorea.palworldkorea_online.post.comment.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/*/{post-id}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommonResponse<?> createComment(@PathVariable("post-id") Long postId, @RequestBody CommentDto.Request commentRequestDto) {
        return CommonResponse.success(SuccessCode.UPDATE_COMMENT_SUCCESS, commentService.createComment(postId, commentRequestDto));
    }

    @PatchMapping("/{comment-id}")
    public CommonResponse<?> updateComment(@PathVariable("comment-id") Long commentId, @RequestBody CommentDto.Request commentRequestDto) {
        return CommonResponse.success(SuccessCode.UPDATE_COMMENT_SUCCESS, commentService.updateComment(commentId, commentRequestDto));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> deleteComment(@PathVariable("post-id") Long postId, @PathVariable("id") Long commentId) {
        return CommonResponse.success(SuccessCode.DELETE_COMMENT_SUCCESS, commentService.deleteComment(postId, commentId));
    }
}
