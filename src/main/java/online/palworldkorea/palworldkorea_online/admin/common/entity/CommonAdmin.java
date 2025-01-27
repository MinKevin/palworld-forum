package online.palworldkorea.palworldkorea_online.admin.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.AdminInventoryType;
import online.palworldkorea.palworldkorea_online.global.domain.BaseTimeEntity;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CommonAdmin extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AdminInventoryType adminInventoryType;

    private String content;

    @OneToMany(mappedBy = "commonAdmin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments;

    public CommonAdmin(AdminInventoryType adminInventoryType) {
        this.adminInventoryType = adminInventoryType;
    }

    public void update(String content, List<Attachment> attachments) {
        this.content = content;
        this.attachments = attachments;
    }
}
