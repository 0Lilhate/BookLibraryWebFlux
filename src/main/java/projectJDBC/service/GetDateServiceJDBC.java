package projectJDBC.service;


import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import projectJDBC.controler.ControlMessenger;
import projectJDBC.dao.author.AuthorDao;
import projectJDBC.dao.book.BookDao;
import projectJDBC.dao.genre.GenreDao;
import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Comment;
import projectJDBC.domain.Genre;
import projectJDBC.repository.author.AuthorRepository;
import projectJDBC.repository.book.BookRepository;
import projectJDBC.repository.book.BookRepositoryImpl;
import projectJDBC.repository.genre.GenreRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GetDateServiceJDBC implements GetDateService {

    private BookRepository bookRepository;
    private ControlMessenger controlMessenger;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;


    public GetDateServiceJDBC(BookRepository bookRepository, AuthorRepository authorRepository,
                              GenreRepository genreRepository, ControlMessenger controlMessenger) {
        this.bookRepository = bookRepository;
        this.controlMessenger = controlMessenger;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }



    @Override
    @Transactional
    public void insertBook() {

        controlMessenger.greetingString("Введите название книги: ");
        String nameBook = controlMessenger.readLine();

        controlMessenger.greetingString("Введите год выпуска книги: ");
        String dateBook = controlMessenger.readLine();

        controlMessenger.greetingString("Введите псевдоним автора книги: ");
        String nameAuthor = controlMessenger.readLine();

        controlMessenger.greetingString("Введите дату рождения автора книги: ");
        String yearAuthor = controlMessenger.readLine();

        controlMessenger.greetingString("Введите жанры книге через запятую: ");
        String[] genresName = controlMessenger.readLine().split(",");
        List<Genre> genreList = new ArrayList<>();
        for (int i=0; i<genresName.length; i++){
            genreList.add(genreRepository.getByNameOrCreate(new Genre(genresName[i])));
        }

        controlMessenger.greetingString("Введите комментарий к книге, если хотите: ");
        String comment =  controlMessenger.readLine();

        List<Comment> commentSingleton = Collections.singletonList(new Comment(comment));



        Book insertBook = new Book(nameBook, dateBook,
                authorRepository.getByNameOrCreate(new Author(nameAuthor, yearAuthor)), genreList, commentSingleton);
        bookRepository.save(insertBook);


    }


    @Transactional
    @Override
    public void getAllBook() {
        List<Book> bookList = bookRepository.getAllBookWithComment();
        for (Book temp : bookList) {
            System.out.println(temp);
        }
    }

    @Override
    public void updateNameBookById() {
        controlMessenger.greetingString("Введите id книги, которое хотите изменить: ");
        String id = controlMessenger.readLine();

        controlMessenger.greetingString("Введите название книги, на которое хотите изменить: ");
        String nameBook = controlMessenger.readLine();
        bookRepository.updateNameById(Long.parseLong(id), nameBook);


    }

    @Transactional
    @Override
    public void deleteBookById() {
        controlMessenger.greetingString("Введите id книги, которое хотите удалить: ");
        String id = controlMessenger.readLine();
        bookRepository.deleteById(Long.parseLong(id));
    }

    @Transactional
    @Override
    public Book getBookById() {
        controlMessenger.greetingString("Введите id книги, которое хотите получить: ");
        String id = controlMessenger.readLine();
        Book book = bookRepository.getById(Long.parseLong(id));
        if(book.getComments() == null){
            controlMessenger.greetingString("{id=" + book.getId_book() + ", name = " + book.getName()
                    + ", date = " + book.getDate() + ", author = " + book.getAuthor().getAuthorName() +
                    "genre = " + book.getGenreList().toString() + "}\n");
        }else {
            controlMessenger.greetingString(book.toString()+"\n");
        }
        return book;

    }


}
