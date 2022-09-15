package projectJDBC.changelogs;


import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.github.cloudyrock.spring.v5.EnableMongock;
import com.mongodb.client.MongoDatabase;
import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Genre;
import projectJDBC.repository.author.AuthorRepository;
import projectJDBC.repository.book.BookRepository;
import projectJDBC.repository.genre.GenreRepository;

import java.util.Arrays;

@ChangeLog(order = "001")
public class InitMongoDBData {
    private static final String INSERT_DATE_BOOK = "2000-08-11";
    private Genre RomansGenre;
    private Genre DramaGenre;

    private Author Lermontov;


    @ChangeSet(order = "000", id = "dropDB", author = "daniil", runAlways = true)
    public void dropDB(MongoDatabase database){
        database.drop();
    }

    @ChangeSet(order = "001", id = "initGenre", author = "daniil", runAlways = true)
    public void initGenre(GenreRepository repository){
        RomansGenre = repository.save(new Genre("Romans"));
        DramaGenre = repository.save(new Genre("Drama"));
    }

    @ChangeSet(order = "002", id = "initAuthor", author = "daniil", runAlways = true)
    public void initAuthor(AuthorRepository repository){
        Lermontov = repository.save(new Author("Lermontov", "1993-05-11"));
    }

    @ChangeSet(order = "003", id = "initBook", author = "daniil", runAlways = true)
    public void initBook(BookRepository repository){
        Book book = new Book("1","Eva", INSERT_DATE_BOOK, Lermontov,
                Arrays.asList(RomansGenre, DramaGenre), null);

        repository.save(book);
    }



}
