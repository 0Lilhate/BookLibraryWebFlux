package projectJDBC.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import projectJDBC.domain.Author;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private String id_book;
    private String name;
    private String date;
    private String authorName;
    private String authorYear;
    private List<String> genres;
    private List<String> commentsId;


}
