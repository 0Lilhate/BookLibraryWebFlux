package projectJDBC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_genre;
    @Column(name = "name_genre", nullable = false)
    private String name_genre;

    public Genre(String name_genre){
        this.name_genre = name_genre;
    }




    @Override
    public String toString() {
        return "genre = " + name_genre;
    }
}
