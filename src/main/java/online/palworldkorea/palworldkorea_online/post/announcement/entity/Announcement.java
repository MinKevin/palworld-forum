package online.palworldkorea.palworldkorea_online.post.announcement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;

import java.util.List;

@Entity
@NoArgsConstructor
public class Announcement extends CommonPost {
    public Announcement(Member author, @NotBlank String title, @NotBlank String content, List<Attachment> attachments) {
        super(author, title, content, attachments);
    }

}
