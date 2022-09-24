package projectJDBC.repository.genre;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import projectJDBC.domain.Genre;
import reactor.core.publisher.Mono;


import java.util.List;

public class GenreRepositoryImpl implements GenreRepositoryCustom {



    @Autowired
    private ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<Genre> getByNameOrCreate(Genre genre) {

        Query query = new Query();
        query.addCriteria(Criteria.where("genreList").is(genre.getName_genre()));

        Mono<Genre> genreMono = mongoTemplate.findOne(query, Genre.class);

        return genreMono.switchIfEmpty(mongoTemplate.save(genre));
    }




}
