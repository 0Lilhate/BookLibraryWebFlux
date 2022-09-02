package projectJDBC.dao.genre;

import projectJDBC.domain.Genre;

import java.util.List;

public interface GenreDao {
    List<Genre> getAllGenre();
    void updateGenre(Genre genre);
    void deleteByIdGenre(long id);
    void insertGenre(Genre genre);
    Genre getByNameOrCreate(Genre genre);
}
