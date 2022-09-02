package projectJDBC.repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJDBC.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {

}
