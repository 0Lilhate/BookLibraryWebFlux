package projectJDBC.service;

import lombok.val;
import org.springframework.stereotype.Service;
import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Comment;
import projectJDBC.domain.Genre;
import projectJDBC.dto.BookDto;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParsingDtoImpl implements ParsingDtoBook{
    @Override
    public BookDto bookToDtoBook(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId_book(book.getId());
        bookDto.setName(book.getName());
        bookDto.setDate(book.getDate());
        bookDto.setAuthorName(book.getAuthor().getAuthorName());
        bookDto.setAuthorYear(book.getAuthor().getYear());

        List<String> genresName = new ArrayList<>();

        for (val tempGenre: book.getGenreList()){
            genresName.add(tempGenre.getName_genre());
        }
        bookDto.setGenres(genresName);


        List<String> commentsId = new ArrayList<>();
        for (val comment: book.getComments()){
            commentsId.add(comment.getId());
        }
        bookDto.setCommentsId(commentsId);

        return bookDto;
    }

    @Override
    public Book bookDtoToBook(BookDto bookDto) {
        Book book = new Book();

        book.setId(bookDto.getId_book());
        book.setName(bookDto.getName());
        book.setDate(bookDto.getDate());
        book.setAuthor(new Author(bookDto.getAuthorName(), bookDto.getAuthorYear()));
        List<Genre> genres = new ArrayList<>();
        for(int i = 0; i<bookDto.getGenres().size(); i++){
            genres.add(new Genre( bookDto.getGenres().get(i)));
        }
        book.setGenreList(genres);

        List<Comment> comments = new ArrayList<>();
        for(int i = 0; i<bookDto.getCommentsId().size(); i++){
            comments.add(new Comment(bookDto.getCommentsId().get(i)));
        }
        book.setComments(comments);

        return book;
    }
}
