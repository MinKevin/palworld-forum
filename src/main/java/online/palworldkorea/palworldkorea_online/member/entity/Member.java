package online.palworldkorea.palworldkorea_online.member.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "member")
@SQLDelete(sql = "UPDATE member SET is_deleted = true where id = ?")
@SQLRestriction("is_deleted is False")
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String password;

    private String nickname;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommonPost> posts;

    @ColumnDefault(value = "false")
    private boolean isDeleted;

    public Member(String email, String password, String nickname, MemberRole memberRole) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.memberRole = memberRole;
    }

    @Override
    public List<MemberRole> getAuthorities() {
        return List.of(memberRole);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public boolean checkInputPasswordIsCorrect(String inputPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(inputPassword, this.password);
    }

    public void changeMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }

    public void updatePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateMemberRole(MemberRole memberRole) {
        this.memberRole = memberRole;
    }
}
