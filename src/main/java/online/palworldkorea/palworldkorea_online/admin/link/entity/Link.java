package online.palworldkorea.palworldkorea_online.admin.link.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.admin.LinkType;

@Entity
@Getter
@NoArgsConstructor
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LinkType linkType;

    private String url;

    public Link(LinkType linkType) {
        this.linkType = linkType;
    }

    public void update(String url) {
        this.url = url;
    }
}
