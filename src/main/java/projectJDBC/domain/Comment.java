package projectJDBC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("comment")
public class Comment {


    public Comment(String id_comment, String text) {
        this.id = id_comment;
        this.text = text;
    }
    public Comment(String text) {
        this.text = text;
    }

    @Id
    private String id;


    @Field("text")
    private String text;

    @Field("book")
    @DBRef(lazy = true)
    private Book book;



    @Override
    public String toString() {
        return "text = " + text;
    }
}
