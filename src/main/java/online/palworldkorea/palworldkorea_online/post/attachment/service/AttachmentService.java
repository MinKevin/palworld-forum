package online.palworldkorea.palworldkorea_online.post.attachment.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.exception.custom_exception.SaveAttachmentFailedException;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
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
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttachmentService {
    private final AttachmentRepository attachmentRepository;

    @Value("${file.upload-dir}")
    private String uploadDirectory;

    public Attachment saveAttachment(Member author, MultipartFile attachment) {
        String fileName = generateUniqueFileName(Objects.requireNonNull(attachment.getOriginalFilename()));
        Path targetLocation = Path.of(uploadDirectory + fileName);

        saveAttachmentToLocal(attachment, targetLocation);

        Attachment attachmentEntity = new Attachment(author, fileName, attachment.getContentType(), attachment.getSize(), targetLocation.toString());
        attachmentRepository.save(attachmentEntity);

        return attachmentEntity;
    }

    public List<Attachment> getAttachments(List<MultipartFile> multipartFiles, Member author) {
        if (multipartFiles == null)
            return new ArrayList<>();

        return multipartFiles.stream()
                .map(multipartFile -> saveAttachment(author, multipartFile))
                .toList();
    }

    public void deleteAttachments(List<Attachment> attachments) {
        attachmentRepository.deleteAll(attachments);
    }

    private String generateUniqueFileName(String originalFilename) {
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        return UUID.randomUUID() + extension;
    }

    private void saveAttachmentToLocal(MultipartFile attachment, Path targetLocation) {
        try {
            System.out.println(targetLocation);
            if (!Files.exists(targetLocation.getParent())) {
                Files.createDirectories(targetLocation.getParent());
            }
            Files.copy(attachment.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new SaveAttachmentFailedException();
        }
    }
}
