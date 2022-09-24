package projectJDBC.repository.author;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;


import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import projectJDBC.domain.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public class AuthorRepositoryImpl implements AuthorRepositoryCustom   {



    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Author> getByNameOrCreate(Author author) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(author.getAuthorName()));
        Flux<Author> authorFlux = reactiveMongoTemplate.find(query, Author.class);

        return Mono.from(authorFlux).switchIfEmpty(reactiveMongoTemplate.save(author));
    }
}
