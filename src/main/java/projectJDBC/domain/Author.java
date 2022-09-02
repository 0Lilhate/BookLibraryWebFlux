package projectJDBC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_author;
    @Column(name = "author_name", nullable = false)
    private String authorName;

    @Column(name = "year_author", nullable = false)
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
