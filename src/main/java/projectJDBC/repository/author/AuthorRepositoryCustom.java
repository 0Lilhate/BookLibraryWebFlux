package projectJDBC.repository.author;

import projectJDBC.domain.Author;
import reactor.core.publisher.Mono;

public interface AuthorRepositoryCustom {
    Mono<Author> getByNameOrCreate(Author author);
}
