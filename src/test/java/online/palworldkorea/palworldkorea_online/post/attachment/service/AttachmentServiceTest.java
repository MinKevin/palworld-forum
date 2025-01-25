package online.palworldkorea.palworldkorea_online.post.attachment.service;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.post.attachment.dto.AttachmentDto;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.yaml")
class AttachmentServiceTest {
    @Autowired
    private AttachmentService attachmentService;

    private Member author;
    private MultipartFile attachment;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        author = new Member("test@test.com", "123", "testNick", MemberRole.USER_LEVEL0);

        MultipartFile multipartFile = mock(MultipartFile.class);
        InputStream mockInputStream = new ByteArrayInputStream("test data".getBytes());

        when(multipartFile.getOriginalFilename()).thenReturn("testImage.png");
        when(multipartFile.getContentType()).thenReturn("image/png");
        when(multipartFile.getSize()).thenReturn(1024L);
        when(multipartFile.getInputStream()).thenReturn(mockInputStream);

        attachment = multipartFile;
    }

    @Test
    void testSaveAttachment() {
        Attachment attachment = attachmentService.saveAttachment(author, this.attachment);

        assert attachment.getAuthor() == author;
        assert attachment.getFileSize() == 1024L;
        assert attachment.getFileType().equals("image/png");
    }
}