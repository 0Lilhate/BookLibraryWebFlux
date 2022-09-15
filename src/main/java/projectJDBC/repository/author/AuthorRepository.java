package projectJDBC.repository.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import projectJDBC.domain.Author;

public interface AuthorRepository extends MongoRepository<Author, String>, AuthorRepositoryCustom {

}
