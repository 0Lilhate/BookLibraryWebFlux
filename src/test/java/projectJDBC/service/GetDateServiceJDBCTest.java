package projectJDBC.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import projectJDBC.controler.ControlMessenger;
import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Comment;
import projectJDBC.domain.Genre;
import projectJDBC.repository.author.AuthorRepository;
import projectJDBC.repository.book.BookRepository;
import projectJDBC.repository.comment.CommentRepository;
import projectJDBC.repository.genre.GenreRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class GetDateServiceJDBCTest {

    private static final String INSERT_NAME_AUTHOR = "Pushkin";
    private static final String INSERT_YEAR_AUTHOR = "1993-05-11";
    private static final String INSERT_NAME_GENRE1 = "Drama";
    private static final String INSERT_NAME_GENRE2 = "Horror";
    private static final String INSERT_NAME_BOOK = "Evangelion";
    private static final String INSERT_DATE_BOOK = "1999-05-11";
    private static final String INSERT_COMMENT = "My comment";
    private static final String YEAR_DATE_BOOK = "1983-02-16";
    private static final String INSERT_DATE_BOOK2 = "1889-11-11";
    @MockBean
    AuthorRepository authorRepository;
    @MockBean
    BookRepository bookRepository;
    @MockBean
    CommentRepository commentRepository;
    @MockBean
    GenreRepository genreRepository;

    @MockBean
    ControlMessenger controlMessenger;

    @Autowired
    GetDateServiceJDBC getDateServiceJDBC;




    @Test
    void shouldInsertBook() {

        when(authorRepository.getByNameOrCreate(any(Author.class))).thenReturn(
                new Author(INSERT_NAME_AUTHOR, INSERT_YEAR_AUTHOR));

        when(genreRepository.getByNameOrCreate(any(Genre.class))).thenReturn(
                new Genre(INSERT_NAME_GENRE1));
        when(genreRepository.getByNameOrCreate(any(Genre.class))).thenReturn(
                 new Genre(INSERT_NAME_GENRE2));
        when(commentRepository.save(any(Comment.class))).thenReturn(
                new Comment(INSERT_NAME_GENRE2));

        when(controlMessenger.readLine()).thenReturn(INSERT_NAME_BOOK).thenReturn(YEAR_DATE_BOOK).thenReturn(INSERT_NAME_AUTHOR)
                        .thenReturn(INSERT_YEAR_AUTHOR).thenReturn(INSERT_NAME_GENRE1,INSERT_NAME_GENRE2).thenReturn(INSERT_COMMENT);


        when(bookRepository.save(any(Book.class))).thenReturn(new Book(INSERT_NAME_BOOK, INSERT_DATE_BOOK,
               new Author(INSERT_NAME_AUTHOR, INSERT_YEAR_AUTHOR),
                Arrays.asList(new Genre(INSERT_NAME_GENRE1), new Genre(INSERT_NAME_GENRE1))
                , Collections.singletonList(new Comment(INSERT_COMMENT))));


        Book expected = bookRepository.save(any(Book.class));


        assertThat(getDateServiceJDBC.insertBook()).isEqualTo(expected);

    }



    @Test
    void getAllBook() {
//        List<Book> bookList = Collections.singletonList(new Book());
//
//        when(bookRepository.findAll()).thenReturn(bookList);
//
//        when(controlMessenger.greetingString("dada"))


    }

    @Test
    void updateNameBookById() {
    }

    @Test
    void deleteBookById() {
    }

    @Test
    void getBookById() {
//        List<Genre> genreList = new ArrayList<>(2);
//        genreList.add(new Genre(INSERT_NAME_GENRE1));
//        genreList.add(new Genre(INSERT_NAME_GENRE2));
//
//        List<Comment> comments = Collections.singletonList(new Comment(INSERT_COMMENT));
//
//        List<Book> bookList = new ArrayList<>();
//        bookList.add((new Book(INSERT_NAME_BOOK, INSERT_DATE_BOOK,
//               new Author(INSERT_NAME_AUTHOR, INSERT_YEAR_AUTHOR), genreList, comments)));
//
//        bookList.add((new Book(INSERT_NAME_BOOK, INSERT_DATE_BOOK2,
//                new Author(INSERT_NAME_AUTHOR, INSERT_YEAR_AUTHOR), genreList, comments)));
//
//
//        when(controlMessenger.readLine()).thenReturn(INSERT_NAME_BOOK);
//        when(bookRepository.findBookByName(INSERT_NAME_BOOK)).thenReturn()
    }
}