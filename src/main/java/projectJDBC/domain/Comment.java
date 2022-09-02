package projectJDBC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {


    public Comment(long id_comment, String text) {
        this.id_comment = id_comment;
        this.text = text;
    }
    public Comment(String text) {
        this.text = text;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_comment;

    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private Book book;



    @Override
    public String toString() {
        return "text = " + text;
    }
}
