package projectJDBC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.Collections;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("book")
public class Book {

    public Book(String name, String date, Author author, List<Genre> genreList, Comment comment){
        this.name = name;
        this.date = date;
        this.author = author;
        this.genreList = genreList;
        this.comments = Collections.singletonList(comment);
    }

    public Book(String name, String date, Author author, List<Genre> genres, List<Comment> comments){

        this.name = name;
        this.date = date;
        this.author = author;
        this.genreList = genres;
        this.comments = comments;
    }
    public Book(String name, String date, Author author, Genre genre, Comment comment){
        this.name = name;
        this.date = date;
        this.author = author;
        this.genreList = Collections.singletonList(genre);
        this.comments = Collections.singletonList(comment);
    }

    public Book(String idBook, String name, String year, Genre genre, Author author, Comment comments) {
        this.id = idBook;
        this.name = name;
        this.date = year;
        this.genreList = Collections.singletonList(genre);
        this.author = author;
        this.comments = Collections.singletonList(comments);
    }



    @Id
    private String id;
    @Field("name_book")
    private String name;

    @Field("date")
    private String date;

    @Field("author_name")
    private Author author;

    @Field("genre")
    private List<Genre> genreList;

    @DBRef(lazy = true)
    @Field("comments")
    private List<Comment> comments;



    @Override
    public String toString() {
        return "{id=" + id + ", name = " + name + ", date = " + date + ", author = " + author.getAuthorName() +
               "genre = " + genreList.toString() + "comments = " + comments.toString() + "}";
    }

}
