package projectJDBC.repository.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import projectJDBC.domain.Author;
import projectJDBC.domain.Genre;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class GenreRepositoryImplTest {
    private static final long FIRST_ID = 1;
    private static final String NAME_GENRE_CREATE = "aaaaaa";
    @Autowired
    TestEntityManager em;

    @Autowired
    GenreRepository genreRepository;

    @Test
    void getByNameOrCreate() {
        Genre actualGet = em.find(Genre.class, FIRST_ID);
        assertThat(actualGet).isEqualTo(genreRepository.getByNameOrCreate(actualGet));

        Genre actualInCreate = new Genre(NAME_GENRE_CREATE);
        assertThat(actualInCreate.getName_genre()).isEqualTo(genreRepository.getByNameOrCreate(actualInCreate).getName_genre());

    }
}