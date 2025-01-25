package online.palworldkorea.palworldkorea_online.email.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.email.dto.EmailVerificationDto;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.SendReportEmailFailedException;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.report.dto.ReportDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailService {
    private final VerificationCodeService verificationCodeService;
    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String adminEmail;

    public void sendVerificationCode(EmailVerificationDto.Request emailVerificationRequestDto) {
        SimpleMailMessage message = makeMailMessage(emailVerificationRequestDto.getEmail());

        emailSender.send(message);
    }

    public void sendReportToAdmin(ReportDto.Request reportRequestDto) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

            messageHelper.setTo(adminEmail);
            messageHelper.setSubject(reportRequestDto.getTitle());
            messageHelper.setText(reportRequestDto.getContent(), true);
            messageHelper.setFrom(MemberUtil.getEmail());

            List<MultipartFile> attachments = reportRequestDto.getAttachments();
            if (attachments != null) {
                for (MultipartFile attachment : attachments) {
                    messageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), attachment);
                }
            }
            emailSender.send(mimeMessage);
        } catch(Exception e) {
            throw new SendReportEmailFailedException();
        }
    }

    private SimpleMailMessage makeMailMessage(String recipientEmail) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(adminEmail);
        message.setTo(recipientEmail);
        message.setSubject("Verification Code");
        message.setText("Your verification code is " + verificationCodeService.generateVerificationCode(recipientEmail));

        return message;
    }

    public boolean verifyCode(EmailVerificationDto.Request emailVerificationRequestDto) {
        return verificationCodeService.validateVerificationCode(emailVerificationRequestDto.getEmail(), emailVerificationRequestDto.getVerificationCode());
    }
}
