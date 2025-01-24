package online.palworldkorea.palworldkorea_online.post.data.entity;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;

import java.util.List;

@Entity
@NoArgsConstructor
public class Data extends CommonPost {
    public Data(Member author, @NotBlank String title, @NotBlank String content, List<Attachment> attachments) {
        super(author, title, content, attachments);
    }
}
