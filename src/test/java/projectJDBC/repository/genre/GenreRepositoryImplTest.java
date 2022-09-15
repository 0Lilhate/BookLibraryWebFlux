package projectJDBC.repository.genre;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import projectJDBC.domain.Genre;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
class GenreRepositoryImplTest {
    private static final long FIRST_ID = 1;
    private static final String NAME_GENRE_CREATE1 = "Romans";


    @Autowired
    GenreRepository genreRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void getByNameOrCreateUnique() {

        Genre actualGenre = genreRepository.getByNameOrCreate(new Genre(NAME_GENRE_CREATE1));
        Query query = new Query();
        query.addCriteria(Criteria.where("name_genre").is(NAME_GENRE_CREATE1));
        Genre expectedGenre = mongoTemplate.findOne(query, Genre.class);

        assertThat(actualGenre).isEqualTo(expectedGenre);

    }

    @Test
    void getByNameOrCreateNotUnique(){
        Genre expectedGenre = mongoTemplate.save(new Genre(NAME_GENRE_CREATE1));

        Genre actualGenre = genreRepository.getByNameOrCreate(new Genre(NAME_GENRE_CREATE1));

        assertThat(actualGenre).isEqualTo(expectedGenre);


    }
}