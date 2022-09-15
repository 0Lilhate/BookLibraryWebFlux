package projectJDBC.repository.genre;

import projectJDBC.domain.Genre;

public interface GenreRepositoryCustom {
    Genre getByNameOrCreate(Genre genre);

}
