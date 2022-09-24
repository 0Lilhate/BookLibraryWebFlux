package projectJDBC.repository.author;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import projectJDBC.domain.Author;

public interface AuthorRepository extends ReactiveMongoRepository<Author, String>, AuthorRepositoryCustom {

}
