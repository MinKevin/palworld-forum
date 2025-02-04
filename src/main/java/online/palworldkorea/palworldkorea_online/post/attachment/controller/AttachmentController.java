package online.palworldkorea.palworldkorea_online.post.attachment.controller;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import online.palworldkorea.palworldkorea_online.post.attachment.service.AttachmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attachments")
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping
    public CommonResponse<?> saveAttachment(@RequestPart(name = "attachment") MultipartFile attachment) {
        return CommonResponse.success(SuccessCode.SAVE_ATTACHMENT_SUCCESS, attachmentService.saveAttachment(attachment));
    }

    @DeleteMapping("/{attachment-id}")
    public CommonResponse<?> deleteAttachment(@PathVariable(name = "attachment-id") Long attachmentId) {
        return CommonResponse.success(SuccessCode.DELETE_ATTACHMENT_SUCCESS, attachmentService.deleteAttachment(attachmentId));
    }
}
