package projectJDBC.handlers;

import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Genre;
import projectJDBC.dto.BookDto;
import projectJDBC.repository.book.BookRepository;
import projectJDBC.service.ParsingDtoBook;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ContextConfiguration(classes = {BookRoute.class, BookHandler.class})
@WebFluxTest
class BookRouteTest {

    @MockBean
    private ParsingDtoBook parsingDtoBook;
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private WebTestClient webTestClient;

    private static final String NAME_BOOK = "Eva";
    private static final String ID_BOOK = "1";
    private static final String DATE_BOOK = "2000-01-02";
    private static final String NAME_GENRE = "Drama";
    private static final String AUTHOR_NAME = "Lermontov";
    private static final String AUTHOR_YEAR = "1989-05-06";
    private static final String ID_BOOK2 = "2";
    private static final String NAME_BOOK2 = "Anime";
    private static final String DATE_BOOK2 = "1999-10-22";
    private static final String NAME_GENRE2 = "Romans";
    private static final String AUTHOR_NAME2 = "Pushkin";
    private static final String AUTHOR_YEAR2 = "1985-05-21";

    @Test
    void shouldFindByAll(){
        val book1 = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        val book2 = new Book(ID_BOOK2, NAME_BOOK2, DATE_BOOK2, new Genre(NAME_GENRE2), new Author(AUTHOR_NAME2, AUTHOR_YEAR2));


        Flux<Book> bookFlux = Flux.just(book1, book2);
        when(bookRepository.findAll()).thenReturn(bookFlux);

        when(parsingDtoBook.bookToDtoBook(any())).thenReturn(new BookDto(ID_BOOK, NAME_BOOK, DATE_BOOK
                , AUTHOR_NAME, AUTHOR_YEAR, List.of(NAME_GENRE))).thenReturn(new BookDto(ID_BOOK2, NAME_BOOK2, DATE_BOOK2
                , AUTHOR_NAME2, AUTHOR_YEAR2, List.of(NAME_GENRE2)));

        webTestClient.get()
                .uri("/book")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(BookDto.class)
                .value(books -> {
                    assertThat(books.get(0).getId_book()).isEqualTo(ID_BOOK);
                    assertThat(books.get(1).getId_book()).isEqualTo(ID_BOOK2);
                });
    }

    @Test
    void shouldFindByID(){
        val book = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        Mono<Book> bookMono = Mono.just(book);
        when(bookRepository.findById(book.getId())).thenReturn(bookMono);
        when(parsingDtoBook.bookToDtoBook(any())).thenReturn(new BookDto(ID_BOOK, NAME_BOOK, DATE_BOOK
                , AUTHOR_NAME, AUTHOR_YEAR, List.of(NAME_GENRE)));

        webTestClient.get()
                .uri("/book/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .value(bookDto -> assertThat(bookDto.getId_book()).isEqualTo(ID_BOOK));

    }

    @Test
    void shouldCreateBook(){

        val book = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        val bookDto = new BookDto(ID_BOOK, NAME_BOOK, DATE_BOOK
                , AUTHOR_NAME, AUTHOR_YEAR, List.of(NAME_GENRE));

        Mono<Book> bookMono = Mono.just(book);
        when(bookRepository.save(book)).thenReturn(bookMono);

        when(parsingDtoBook.bookToDtoBook(any())).thenReturn(bookDto);

        when(parsingDtoBook.bookDtoToBook(any())).thenReturn(book);


        webTestClient.post()
                .uri("/book")
                .body(Mono.just(bookDto), BookDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BookDto.class)
                .value(bookDto1 -> assertThat(bookDto1.getId_book()).isEqualTo(ID_BOOK));
    }

    @Test
    void shouldDeleteBookById(){
        val book = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        Mono<Book> bookMono = Mono.just(book);
        Mono<Void> deleteMono = bookMono.then();
        when(bookRepository.findById(ID_BOOK)).thenReturn(bookMono);
        when(bookRepository.delete(book)).thenReturn(deleteMono);

        webTestClient.delete()
                .uri("/book/delete/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .value(bookDto -> assertThat(bookDto).isNull());
    }


    //Не работает.
    @Test
    void shouldUpdateBook(){
        val book = new Book(ID_BOOK, NAME_BOOK, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        val bookDto = new BookDto(ID_BOOK, NAME_BOOK, DATE_BOOK
                , AUTHOR_NAME, AUTHOR_YEAR, List.of(NAME_GENRE));

        Book updateBook = new Book(ID_BOOK, NAME_BOOK2, DATE_BOOK
                , new Genre(NAME_GENRE), new Author(AUTHOR_NAME, AUTHOR_YEAR));

        Mono<Book> bookMono = Mono.just(book);

        when(bookRepository.findById(ID_BOOK)).thenReturn(bookMono);
        when(bookRepository.save(book)).thenReturn(Mono.just(updateBook));

        when(parsingDtoBook.bookToDtoBook(any())).thenReturn(bookDto);
        when(parsingDtoBook.bookDtoToBook(any())).thenReturn(book);




        webTestClient.put()
                .uri("/book/change/1")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(updateBook), Book.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BookDto.class)
                .value(bookDto1 -> assertThat(bookDto1.getName()).isEqualTo(NAME_BOOK));


    }
}