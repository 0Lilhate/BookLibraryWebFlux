package projectJDBC.repository.genre;



import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import projectJDBC.domain.Genre;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String>, GenreRepositoryCustom {
}
