package projectJDBC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("author")
public class Author {
    @Id
    @Indexed(unique = true)
    @Field("id")
    private String id_author;

    @Field("author_name")
    @Indexed(unique = true)
    private String authorName;

    @Field("year")
    private String year;


    public Author(String authorName, String year){
        this.authorName = authorName;
        this.year = year;
    }




    @Override
    public String toString() {
        return "id = " + id_author + ", author = " + authorName + ", year = " + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author1 = (Author) o;
        return getId_author() == author1.getId_author() && getAuthorName().equals(author1.getAuthorName()) && getYear().equals(author1.getYear());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_author(), getAuthorName(), getYear());
    }
}
