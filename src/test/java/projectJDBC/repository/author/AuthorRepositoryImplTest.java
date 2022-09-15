package projectJDBC.repository.author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import projectJDBC.domain.Author;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
class AuthorRepositoryImplTest {

    private static final String NAME_AUTHOR_DATA = "Pushkin";
    private static final String NAME_AUTHOR_CREATE = "Kekandos";
    private static final long FIRST_ID = 1;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    void getByNameOrCreateNotUnique() {
        Author expected = mongoTemplate.save(new Author(NAME_AUTHOR_CREATE, NAME_AUTHOR_DATA));
        Author actual = authorRepository.getByNameOrCreate(new Author(NAME_AUTHOR_CREATE, NAME_AUTHOR_DATA));
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void getByNameOrCreateUnique() {

        Author actual = authorRepository.getByNameOrCreate(new Author(NAME_AUTHOR_CREATE, NAME_AUTHOR_DATA));

        Query query = new Query();
        query.addCriteria(Criteria.where("author_name").is(NAME_AUTHOR_CREATE));
        Author expected = mongoTemplate.findOne(query, Author.class);

        assertThat(actual).isEqualTo(expected);

    }
}