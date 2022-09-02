package projectJDBC.dao.book;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Comment;
import projectJDBC.domain.Genre;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@Import(BookDaoJdbc.class)
class BookDaoJDBCTest {

    private static final long FIRST_BOOK_ID = 1L;
    private static final long EXPECTED_QUERIES_COUNT = 2;
    private static final String NAME_BOOK_UPDATE = "Endless summer";
    @Autowired
    private BookDaoJdbc bookDaoJDBC;

    @Autowired
    private TestEntityManager em;

    @DisplayName(" проверка на добавления объекта Book")
    @Test
    void shouldInsertBook(){
        val author = new Author(1, "Schekspir", "1777-01-21");
        val genre = new  Genre(3, "Romans");
        val comment = new Comment(2, "AAAAAAAAAA");
        val book = new Book(3, "Gamlet", "1788-11-22", author, genre, comment);

        bookDaoJDBC.insert(book);

        val actualBook = em.find(Book.class, book.getId_book());

        assertThat(actualBook).isEqualTo(book);



    }

    @DisplayName(" должен загружать информацию о нужном студенте по его id")
    @Test
    void shouldGetByIdBook(){
        val optionalActualBook = bookDaoJDBC.getByIdBook(FIRST_BOOK_ID);
        val expectedBook = em.find(Book.class, FIRST_BOOK_ID);
        Assertions.assertThat(optionalActualBook).isPresent().get()
                .isEqualToComparingFieldByField(expectedBook);

    }

    @DisplayName( "должен удалять заданного книгу по его id")
    @Test
    void shouldDeleteByIdBook() {
        val firstBook = bookDaoJDBC.getByIdBook(FIRST_BOOK_ID);
        assertThat(firstBook).isNotNull();
        em.detach(firstBook.get());

        bookDaoJDBC.deleteById(FIRST_BOOK_ID);
        val detachBook = em.find(Book.class, FIRST_BOOK_ID);

        assertThat(detachBook).isNull();


    }

    @DisplayName("должен загружать список всех студентов с полной информацией о них")
    @Test
    void shouldGetAllBook() {
        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
                .unwrap(SessionFactory.class);
        sessionFactory.getStatistics().setStatisticsEnabled(true);

        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
        val books = bookDaoJDBC.getAll();

        assertThat(books.size()).isEqualTo(2);

        Assertions.assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);

    }

    @Test
    void shouldUpdateBook() {
        bookDaoJDBC.updateNameByID(1L, NAME_BOOK_UPDATE);
        Book actual = em.find(Book.class, 1L);
        assertThat(actual.getName()).isEqualTo(NAME_BOOK_UPDATE);
    }




}