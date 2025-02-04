package online.palworldkorea.palworldkorea_online.post.comment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.global.domain.BaseTimeEntity;
import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.List;

@Entity
@Getter
@SQLDelete(sql = "UPDATE comment SET is_deleted = true where id = ?")
@SQLRestriction("is_deleted is False")
@NoArgsConstructor
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    private CommonPost post;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private List<Comment> childComments;

    @ColumnDefault(value = "false")
    private boolean isDeleted;

    public Comment(Member author, CommonPost post, String content, Comment parent) {
        this.author = author;
        this.post = post;
        this.content = content;
        this.parent = parent;
    }

    public void updateContent(@NotBlank String content) {
        this.content = content;
    }
}
