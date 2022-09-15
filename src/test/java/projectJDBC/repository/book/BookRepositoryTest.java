package projectJDBC.repository.book;

import lombok.val;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Genre;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@EnableConfigurationProperties
@ComponentScan({"projectJDBC.config", "projectJDBC.repository"})
@ExtendWith(SpringExtension.class)
class BookRepositoryTest {
    private static final String BOOK_NAME_KEY = "name_book";

    private static final long EXPECTED_QUERIES_COUNT = 2;
    private static final long FIRST_BOOK_ID = 1L;
    private static final String UPDATE_NAME_BOOK = "UpdateName";
    private static final String INSERT_NAME_AUTHOR = "Pushkin";
    private static final String INSERT_YEAR_AUTHOR = "1993-05-11";
    private static final String INSERT_NAME_GENRE1 = "Drama";
    private static final String INSERT_NAME_GENRE2 = "Horror";
    private static final String INSERT_NAME_BOOK = "Evangelion";
    private static final String INSERT_DATE_BOOK = "1999-05-11";
    private static final String INSERT_COMMENT = "My comment";
    private static final String YEAR_DATE_BOOK = "1983-02-16";

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MongoTemplate mongoTemplate;



//    @Test
//    void getAllBookWithComment(){
//        SessionFactory sessionFactory = em.getEntityManager().getEntityManagerFactory()
//                .unwrap(SessionFactory.class);
//        sessionFactory.getStatistics().setStatisticsEnabled(true);
//
//        System.out.println("\n\n\n\n----------------------------------------------------------------------------------------------------------");
//        val books = bookRepository.getAllBookWithComment();
//
//        assertThat(books.size()).isEqualTo(2);
//
//        Assertions.assertThat(sessionFactory.getStatistics().getPrepareStatementCount()).isEqualTo(EXPECTED_QUERIES_COUNT);
//    }


    @Test
    void updateNameByIdTest(){
//        val book = bookRepository.findBookByName("Eva");
//        assertThat(book).isNull();

        val book = new Book("1", INSERT_NAME_BOOK, INSERT_DATE_BOOK,
                new Author(INSERT_NAME_AUTHOR, INSERT_YEAR_AUTHOR),
                Arrays.asList(new Genre(INSERT_NAME_GENRE1), new Genre(INSERT_NAME_GENRE1))
                , null);

        mongoTemplate.save(book);

        bookRepository.updateBookNameByName(INSERT_NAME_BOOK,UPDATE_NAME_BOOK);
        Query query = new Query();
        query.addCriteria(Criteria.where("name_book").is(UPDATE_NAME_BOOK));
        val actualBook = mongoTemplate.findOne(query,Book.class);
        assertThat(actualBook).isNotNull();

    }

    //Для этого теста нужно создать начальные данные
    @Test
    void findBookByName(){

        val book = new Book("1", INSERT_NAME_BOOK, INSERT_DATE_BOOK,
                new Author(INSERT_NAME_AUTHOR, INSERT_YEAR_AUTHOR),
                Arrays.asList(new Genre(INSERT_NAME_GENRE1), new Genre(INSERT_NAME_GENRE1))
                , null);

        mongoTemplate.save(book);

        Query query = new Query(Criteria.where(BOOK_NAME_KEY).is(INSERT_NAME_BOOK));
        List<Book> actualBook = mongoTemplate.find(query, Book.class);

        assertThat(actualBook).isEqualTo(bookRepository.findBookByName(INSERT_NAME_BOOK));

    }

    @Test
    void deleteBookByName(){

    }





}