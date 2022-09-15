package projectJDBC.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("genre")
public class Genre {
    @Id
    @Field("id_genre")
    private String id_genre;
    @Indexed(unique = true)
    @Field("name_genre")
    private String name_genre;


    public Genre(String name_genre){
        this.name_genre = name_genre;
    }




    @Override
    public String toString() {
        return "genre = " + name_genre;
    }
}
