package online.palworldkorea.palworldkorea_online.admin.coalition.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@NoArgsConstructor
public class Coalition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    private Attachment attachment;

    public void update(String name, Attachment attachment) {
        this.name = name;
        this.attachment = attachment;
    }
}
