package projectJDBC.repository.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import projectJDBC.domain.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AuthorRepositoryImplTest {

    private static final String NAME_AUTHOR_DATA = "Pushkin";
    private static final String NAME_AUTHOR_CREATE = "Kekandos";
    private static final long FIRST_ID = 1;
    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void getByNameOrCreate() {
        Author actualGet = em.find(Author.class, FIRST_ID);
        assertThat(actualGet).isEqualTo(authorRepository.getByNameOrCreate(actualGet));

        Author actualInCreate = new Author(NAME_AUTHOR_CREATE, "2000-01-01");
        assertThat(actualInCreate.getAuthorName()).isEqualTo(authorRepository.getByNameOrCreate(actualInCreate).getAuthorName());

    }
}