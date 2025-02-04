package online.palworldkorea.palworldkorea_online.post.attachment.service;

import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.DeleteAttachmentFailedException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.InvalidAttachmentIdException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.NotEnoughPermissionException;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.SaveAttachmentFailedException;
import online.palworldkorea.palworldkorea_online.global.util.MemberUtil;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.member.service.MemberService;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.attachment.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;
    private final MemberService memberService;
    @Value("${file.upload-dir}")
    private String uploadDirectory;

    public List<Attachment> getAttachments(List<MultipartFile> multipartFiles) {
        if (multipartFiles == null)
            return new ArrayList<>();

        return multipartFiles.stream()
                .map(this::saveAttachment)
                .toList();
    }

    public Attachment saveAttachment(MultipartFile attachment) {
        Member author = memberService.getMemberByEmail(MemberUtil.getEmail());
        String fileName = generateUniqueFileName(Objects.requireNonNull(attachment.getOriginalFilename()));
        Path targetLocation = Path.of(uploadDirectory + fileName);

        saveAttachmentToLocal(attachment, targetLocation);

        Attachment attachmentEntity = new Attachment(author, fileName, attachment.getContentType(), attachment.getSize(), targetLocation.toString());

        attachmentRepository.save(attachmentEntity);

        return attachmentEntity;
    }

    public boolean deleteAttachment(Long attachmentId) {
        Attachment attachment = getAttachmentById(attachmentId);
        Member member = memberService.getMemberByEmail(MemberUtil.getEmail());
        validatePermission(attachment, member);

        deleteAttachmentFromLocal(attachment);

        attachmentRepository.delete(attachment);

        return true;
    }

    public void deleteAttachments(List<Attachment> attachments) {
        attachments.forEach(attachment -> deleteAttachment(attachment.getId()));
    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }

    private void saveAttachmentToLocal(MultipartFile attachment, Path targetLocation) {
        try {
            if (!Files.exists(targetLocation.getParent())) {
                Files.createDirectories(targetLocation.getParent());
            }
            Files.copy(attachment.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new SaveAttachmentFailedException();
        }
    }

    private void deleteAttachmentFromLocal(Attachment attachment) {
        Path filePath = Path.of(attachment.getFilePath());

        try {
            if (Files.exists(filePath))
                Files.delete(filePath);
        } catch (IOException e) {
            throw new DeleteAttachmentFailedException();
        }
    }

    private void validatePermission(Attachment attachment, Member member) {
        if (!attachment.getAuthor().getEmail().equals(member.getEmail()) && member.getMemberRole() != MemberRole.ADMIN) {
            throw new NotEnoughPermissionException();
        }
    }

    private Attachment getAttachmentById(Long attachmentId) {
        return attachmentRepository.findById(attachmentId)
                .orElseThrow(InvalidAttachmentIdException::new);
    }
}
