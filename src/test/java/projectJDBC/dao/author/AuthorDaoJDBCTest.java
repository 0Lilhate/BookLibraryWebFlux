package projectJDBC.dao.author;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import projectJDBC.domain.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@Import(AuthorDaoJDBC.class)
class AuthorDaoJDBCTest {

    private static final long EXPECTED_QUERIES_COUNT = 1L;
    private static final long FIRST_AUTHOR_ID = 1L;
    private static final String NAME_AUTHOR_UPDATE = "Tolstov";
    @Autowired
    private AuthorDaoJDBC authorDaoJDBC;

    @Autowired
    private TestEntityManager em;

    @Test
    void shouldGetAllAuthor() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val authors = authorDaoJDBC.getAllAuthor();

        assertThat(authors.size()).isEqualTo(1L);

        Assertions.assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);


    }

    @Test
    void shouldUpdateAuthor() {
        authorDaoJDBC.updateAuthor(1L, NAME_AUTHOR_UPDATE);
        Author actual = em.find(Author.class, 1L);
        assertThat(actual.getAuthorName()).isEqualTo(NAME_AUTHOR_UPDATE);
    }

    @Test
    void shouldDeleteByIdAuthor() {
        val firstBook = authorDaoJDBC.getByIdAuthor(FIRST_AUTHOR_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook.get());

        authorDaoJDBC.deleteByIdAuthor(FIRST_AUTHOR_ID);
        val detachAUTHOR = em.find(Author.class, FIRST_AUTHOR_ID);

        assertThat(detachAUTHOR).isNull();
    }

    @Test
    void shouldInsertAuthor() {
        Author author = new Author("Daniil", "2002-04-08");
        authorDaoJDBC.insertAuthor(author);
        Author actual = em.find(Author.class, author.getId_author());

        assertThat(actual).isEqualTo(author);
    }

    @Test
    void shouldGetByIdAuthor(){
        val actualAuthor = authorDaoJDBC.getByIdAuthor(1L).get();
        val expectedBook = em.find(Author.class, 1L);
        assertThat(actualAuthor).isEqualTo(expectedBook);
    }
}