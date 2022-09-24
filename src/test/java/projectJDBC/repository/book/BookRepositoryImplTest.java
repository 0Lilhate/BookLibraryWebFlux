package projectJDBC.repository.book;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Genre;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.beans.Transient;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
class BookRepositoryImplTest {

    private static final String NAME_BOOK = "Eva";
    private static final String ID_BOOK = "1";
    private static final String DATE_BOOK = "2000-01-02";
    private static final String NAME_GENRE = "Drama";
    private static final String AUTHOR_NAME = "Lermontov";
    private static final String AUTHOR_YEAR = "1989-05-06";
    private static final String ID_BOOK1 = "2";

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSetIdOnSave() {
        Mono<Book> bookMono = bookRepository.save(new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR)));

        StepVerifier.create(bookMono)
                .assertNext(Assertions::assertNotNull)
                .expectComplete()
                .verify();
    }

    @Test
    void shouldFindById(){
        bookRepository.save(new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR))).block();
        StepVerifier.create(
                bookRepository.findById(ID_BOOK)
        )
                .assertNext(book -> assertThat(book).isNotNull())
                .expectComplete()
                .verify();
    }

    @Test
    void shouldFindByAll(){
        List<Book> bookList = List.of(new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR)));

        bookRepository.insert(bookList).subscribe();

        StepVerifier.create(
                        bookRepository.findAll()
                )
                .expectNextCount(1)
                .expectComplete()
                .verify();
    }

    @Test
    void shouldDeleteById(){
        bookRepository.save(new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR))).subscribe();
        StepVerifier.create(
                        bookRepository.deleteById(ID_BOOK)
                )
                .expectNextCount(0)
                .expectComplete()
                .verify();
    }




}