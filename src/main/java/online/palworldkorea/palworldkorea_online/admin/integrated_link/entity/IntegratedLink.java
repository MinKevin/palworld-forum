package online.palworldkorea.palworldkorea_online.admin.integrated_link.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class IntegratedLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private String title;

    private String url;

    public void update(String title, String url) {
        this.title = title;
        this.url = url;
    }
}
