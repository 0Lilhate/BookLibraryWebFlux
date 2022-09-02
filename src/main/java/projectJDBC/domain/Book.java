package projectJDBC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {

    public Book(long id_book, String name, String date, Author author, Genre genre, Comment comment){
        this.id_book = id_book;
        this.name = name;
        this.date = date;
        this.author = author;
        this.genreList = Collections.singletonList(genre);
        this.comments = Collections.singletonList(comment);
    }

    public Book(String name, String date, Author author, List<Genre> genres, List<Comment> comments){

        this.name = name;
        this.date = date;
        this.author = author;
        this.genreList = genres;
        this.comments = comments;
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_book;

    @Column(name = "name_book")
    private String name;

    @Column(name = "date_book", nullable = false)
    private String date;


    @ManyToOne(targetEntity = Author.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author")
    private Author author;

    @ManyToMany(targetEntity = Genre.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH}, fetch = FetchType.LAZY)
    @JoinTable(name = "book_genre", joinColumns = @JoinColumn(name = "id_genre"),
            inverseJoinColumns = @JoinColumn(name = "id_book"))
    private List<Genre> genreList;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @Override
    public String toString() {
        return "{id=" + id_book + ", name = " + name + ", date = " + date + ", author = " + author.getAuthorName() +
               "genre = " + genreList.toString() + "comments = " + comments.toString() + "}";
    }

}
