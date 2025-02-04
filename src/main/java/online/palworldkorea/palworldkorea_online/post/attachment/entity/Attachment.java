package online.palworldkorea.palworldkorea_online.post.attachment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.common.entity.CommonAdmin;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;

@Entity
@Getter
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Member author;

    private String fileName;
    private String fileType;
    private long fileSize;
    private String filePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private CommonPost post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commonAdmin_id")
    private CommonAdmin commonAdmin;

    public Attachment(Member member, String fileName, String contentType, long size, String string) {
        this.author = member;
        this.fileName = fileName;
        this.fileType = contentType;
        this.fileSize = size;
        this.filePath = string;
    }

    public <E extends CommonPost> void mappingCommonPost(E post) {
        this.post = post;
    }
}
