package online.palworldkorea.palworldkorea_online.post.common.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.domain.BaseTimeEntity;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.attachment.entity.Attachment;
import online.palworldkorea.palworldkorea_online.post.comment.entity.Comment;
import online.palworldkorea.palworldkorea_online.post.common.dto.CommonPostDto;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE common_post SET is_deleted = true where id = ?")
@SQLRestriction("is_deleted is False")
@NoArgsConstructor
public class CommonPost extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, updatable = false)
    private String dtype;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    private String title;

    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attachment> attachments;

    private long hits;

    private long countOfComments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ColumnDefault(value = "false")
    private boolean isDeleted;

    public CommonPost(Member author, @NotBlank String title, @NotBlank String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public <Q extends CommonPostDto.Request> void update(Q postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.content = postRequestDto.getContent();
    }

    public void increaseHit() {
        this.hits++;
    }

    public void increateCountOfComments() {
        this.countOfComments++;
    }

    public void decreaseCountOfComments() {
        this.countOfComments--;
    }
}
