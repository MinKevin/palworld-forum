package online.palworldkorea.palworldkorea_online.report.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.response.CommonResponse;
import online.palworldkorea.palworldkorea_online.global.response.SuccessCode;
import online.palworldkorea.palworldkorea_online.report.dto.ReportDto;
import online.palworldkorea.palworldkorea_online.report.service.ReportService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @PostMapping
    public CommonResponse<?> sendToAdmin(@RequestPart(name = "data") @Valid ReportDto.Request reportRequestDto,
                                         @RequestPart(name = "attachments", required = false) List<MultipartFile> attachments) {
        reportRequestDto.setAttachments(attachments);

        reportService.sendToAdmin(reportRequestDto);
        return CommonResponse.success(SuccessCode.SEND_REPORT_TO_ADMIN_SUCCESS);
    }
}
