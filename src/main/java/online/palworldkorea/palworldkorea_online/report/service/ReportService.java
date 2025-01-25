package online.palworldkorea.palworldkorea_online.report.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.email.service.EmailService;
import online.palworldkorea.palworldkorea_online.report.dto.ReportDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final EmailService emailService;

    public void sendToAdmin(ReportDto.Request reportRequestDto) {
        emailService.sendReportToAdmin(reportRequestDto);
    }
}
