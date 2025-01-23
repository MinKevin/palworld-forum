package online.palworldkorea.palworldkorea_online.global;

import online.palworldkorea.palworldkorea_online.member.entity.Member;
import online.palworldkorea.palworldkorea_online.member.entity.MemberRole;
import online.palworldkorea.palworldkorea_online.post.comment.entity.Comment;
import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.hibernate.Hibernate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestFixture {
    public static List<Member> createMembers() {
        List<Member> members = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            String email = "member" + i + "@palworldkorea.com";
            Member member = new Member(email, "password" + i, "nickname" + i, MemberRole.USER_LEVEL0);
            members.add(member);
        }

        return members;
    }

    public static List<CommonPost> createPosts(List<Member> members) {
        List<CommonPost> posts = new ArrayList<>();

        for (Member member : members) {
            for (int i = 0; i < 10; i++) {
                String title = "Post Title " + i + " by " + member.getEmail();
                String content = "This is the content of post " + i + " written by " + member.getEmail();
                CommonPost post = new CommonPost(member, title, content, new ArrayList<>());
                posts.add(post);
            }
        }

        return posts;
    }

    public static void createComments(List<CommonPost> posts, List<Member> members) throws NoSuchFieldException, IllegalAccessException {
        for (CommonPost post : posts) {
            Hibernate.initialize(post);
            Field field = post.getClass().getDeclaredField("comments");
            field.setAccessible(true);
            field.set(post, new ArrayList<>());

            for (int i = 0; i < 10; i++) {
                String commentContent = "Comment " + i + " on post " + post.getTitle();
                Member author = members.get(i % 10);
                Comment comment = new Comment(author, post, commentContent, null);

                Hibernate.initialize(comment);
                Field field2 = Comment.class.getDeclaredField("childComments");
                field2.setAccessible(true);
                field2.set(comment, new ArrayList<>());

                for (int j = 0; j < 5; j++) {
                    String childCommentContent = "Child comment " + i + " on comment " + comment.getContent();
                    Member author2 = members.get((i + 1) % 10);
                    Comment childComment = new Comment(author2, post, childCommentContent, comment);

                    comment.getChildComments().add(childComment);
                }
                post.getComments().add(comment);
            }
        }
    }

    // 전체 데이터를 생성하는 메서드
    public static List<CommonPost> createData() throws NoSuchFieldException, IllegalAccessException {
        List<Member> members = createMembers();
        List<CommonPost> posts = createPosts(members);
        createComments(posts, members);
        return posts;  // 전체 게시물 반환
    }
}
