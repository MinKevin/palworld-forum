package online.palworldkorea.palworldkorea_online.post.common.repository;

import online.palworldkorea.palworldkorea_online.post.common.entity.CommonPost;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@DataJpaTest
class CommonPostRepositoryTest {
    @Autowired
    private CommonPostRepository<CommonPost> postRepository;

    @ParameterizedTest
    @ValueSource(strings = {"a", ""})
    void testFindByTitleContainingIgnoreCase(String keyword) {
        // given

        // when
        Page<CommonPost> result = postRepository
                .findByTitleContainingIgnoreCase(keyword, PageRequest.of(0, 10));

        // then
        Assertions.assertThat(result.getContent().size()).isNotZero();
        result.getContent()
                .forEach(post ->
                        Assertions.assertThat(post.getTitle().toLowerCase()).contains(keyword));
     }

     @ParameterizedTest
     @ValueSource(strings = {"announcement", "data", "free", "guide", "promotion"})
     void testFindByTitleContainingIgnoreCaseAndDtype(String dtype) {
         // given

         // when
         Page<CommonPost> result = postRepository
                 .findByTitleContainingIgnoreCaseAndDtype("", dtype, PageRequest.of(0, 10));

         // then
         Assertions.assertThat(result.getContent().size()).isNotZero();
         result.getContent()
                 .forEach(post ->
                         Assertions.assertThat(post.getDtype()).isEqualTo(dtype));
      }
}