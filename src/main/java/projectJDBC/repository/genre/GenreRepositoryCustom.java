package projectJDBC.repository.genre;

import projectJDBC.domain.Genre;
import reactor.core.publisher.Mono;

public interface GenreRepositoryCustom {
    Mono<Genre> getByNameOrCreate(Genre genre);

}
