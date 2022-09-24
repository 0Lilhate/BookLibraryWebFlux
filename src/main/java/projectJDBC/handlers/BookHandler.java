package projectJDBC.handlers;

import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import projectJDBC.domain.Book;
import projectJDBC.domain.Genre;
import projectJDBC.dto.BookDto;
import projectJDBC.repository.book.BookRepository;
import projectJDBC.service.ParsingDtoBook;
import reactor.core.publisher.Mono;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.web.reactive.function.server.ServerResponse.*;

@Component
public class BookHandler {
    private final BookRepository bookRepository;
    private ParsingDtoBook parsingDtoBook;

    public BookHandler(BookRepository bookRepository, ParsingDtoBook parsingDtoBook) {
        this.bookRepository = bookRepository;
        this.parsingDtoBook = parsingDtoBook;
    }

    public Mono<ServerResponse> getAllBook(ServerRequest req) {
        return ok().body(this.bookRepository.findAll().map(parsingDtoBook::bookToDtoBook), BookDto.class);
    }

    public Mono<ServerResponse> getBookById(ServerRequest req) {
        return this.bookRepository.findById(req.pathVariable("id")).map(parsingDtoBook::bookToDtoBook)
                .flatMap(book -> ok().body(Mono.just(book), BookDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createBook(ServerRequest request){
        Mono<Book> bookMono = request.bodyToMono(BookDto.class).map(parsingDtoBook::bookDtoToBook);

        return bookMono.flatMap(book -> ServerResponse.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON)
                .body(bookRepository.save(book).map(parsingDtoBook::bookToDtoBook), BookDto.class));
    }

    public Mono<ServerResponse> deleteBookById(ServerRequest request){

        return bookRepository.findById(request.pathVariable("id"))
                .flatMap(book -> ServerResponse.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON)
                        .body(bookRepository.delete(book), Book.class)).switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> updateBook(ServerRequest req) {
        Mono<Book> existingBookMono = bookRepository.findById(req.pathVariable("id"));
        Mono<BookDto> bookMono = req.bodyToMono(BookDto.class);

        return Mono.zip(bookMono,existingBookMono, ((book, existingBook)->(

            new BookDto(existingBook.getId(),book.getName(), book.getDate(),
                    book.getAuthorName(), book.getAuthorYear(), book.getGenres()))))

                .flatMap(bookDto -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(bookRepository.save(parsingDtoBook.bookDtoToBook(bookDto)).map(parsingDtoBook::bookToDtoBook), BookDto.class))
                .switchIfEmpty(ServerResponse.notFound().build());
    }



}
