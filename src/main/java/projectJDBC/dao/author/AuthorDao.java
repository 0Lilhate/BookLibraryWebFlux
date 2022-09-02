package projectJDBC.dao.author;

import projectJDBC.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    List<Author> getAllAuthor();
    void updateAuthor(long id, String name_author);
    void deleteByIdAuthor(long id);
    Author insertAuthor(Author author);
    Optional<Author> getByIdAuthor(long id);
}
