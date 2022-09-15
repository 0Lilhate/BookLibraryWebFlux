package projectJDBC.repository.genre;


import org.springframework.data.mongodb.repository.MongoRepository;
import projectJDBC.domain.Genre;

public interface GenreRepository extends MongoRepository<Genre, String>, GenreRepositoryCustom {
}
