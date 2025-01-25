package online.palworldkorea.palworldkorea_online.post.common.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import online.palworldkorea.palworldkorea_online.post.attachment.dto.AttachmentDto;
import online.palworldkorea.palworldkorea_online.post.common.SearchType;
import online.palworldkorea.palworldkorea_online.post.common.SortType;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import online.palworldkorea.palworldkorea_online.post.common.mapper.CommonPostMapper;
import online.palworldkorea.palworldkorea_online.post.common.service.CommonPostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
public abstract class CommonPostController<
        E extends CommonPost,
        Q extends CommonPostDto.Request,
        P extends CommonPostDto.Response,
        M extends CommonPostMapper<E, Q, P>> {
    protected final CommonPostService<E, Q, P, M> postService;

    @GetMapping("/{id}")
    public CommonResponse<?> getPost(@PathVariable(name = "id") long id) {
        return CommonResponse.success(SuccessCode.GET_POST_SUCCESS, postService.getPost(id));
    }

    @GetMapping
    public CommonResponse<?> searchPosts(@RequestParam(name = "searchType", defaultValue = "NICKNAME") SearchType searchType,
                                         @RequestParam(name = "page", defaultValue = "1") int page,
                                         @RequestParam(name = "limit", defaultValue = "10") int limit,
                                         @RequestParam(name = "keyword", defaultValue = "") String keyword,
                                         @RequestParam(name = "sort", defaultValue = "RECENT") SortType sortType) {
        return CommonResponse.success(SuccessCode.GET_POST_LIST_SUCCESS, postService.searchPosts(searchType, page, limit, keyword, sortType));
    }

    @PostMapping
    public CommonResponse<?> createPost(@RequestPart(name = "data") @Valid Q request,
                                        @RequestPart(name = "attachments", required = false) List<MultipartFile> attachments) {
        request.setAttachments(attachments);

        return CommonResponse.success(SuccessCode.POST_POST_SUCCESS, postService.createPost(request));
    }

    @PatchMapping("/{id}")
    public CommonResponse<?> updatePost(@PathVariable(name = "id") long id,
                                        @RequestPart(name = "data") @Valid Q request,
                                        @RequestPart(name = "attachments", required = false) List<MultipartFile> attachments) {
        request.setAttachments(attachments);

        return CommonResponse.success(SuccessCode.UPDATE_POST_SUCCESS, postService.updatePost(id, request));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<?> deletePost(@PathVariable(name = "id") long id) {
        return CommonResponse.success(SuccessCode.DELETE_POST_SUCCESS, postService.deletePost(id));
    }
}
