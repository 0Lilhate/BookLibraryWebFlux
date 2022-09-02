package projectJDBC.repository.book;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import projectJDBC.domain.Book;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BookRepositoryTest {

    private static final long EXPECTED_QUERIES_COUNT = 2;
    private static final long FIRST_BOOK_ID = 1L;
    private static final String UPDATE_NAME_BOOK = "UpdateName";
    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository bookRepository;


    @Test
    void getAllBookWithComment(){
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val books = bookRepository.getAllBookWithComment();

        assertThat(books.size()).isEqualTo(2);

        Assertions.assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
    }

    @Test
    void updateNameByIdTest(){
        bookRepository.updateNameById(FIRST_BOOK_ID, UPDATE_NAME_BOOK);
        Book actual = em.find(Book.class, FIRST_BOOK_ID);
        assertThat(actual.getName()).isEqualTo(UPDATE_NAME_BOOK);
    }


}