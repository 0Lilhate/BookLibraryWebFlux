package projectJDBC.repository.genre;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJDBC.domain.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>, GenreRepositoryCustom {
}
