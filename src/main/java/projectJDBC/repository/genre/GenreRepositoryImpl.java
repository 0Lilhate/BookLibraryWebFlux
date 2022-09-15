package projectJDBC.repository.genre;

import com.mongodb.client.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import projectJDBC.domain.Author;
import projectJDBC.domain.Genre;


import java.util.List;

public class GenreRepositoryImpl implements GenreRepositoryCustom {



    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Genre getByNameOrCreate(Genre genre) {
        Query queryMongo = new Query();
        queryMongo.addCriteria(Criteria.where("name_genre").is(genre.getName_genre()));
        Genre genreTemp = mongoTemplate.findOne(queryMongo, Genre.class);


        if (genreTemp != null){
            return genreTemp;
        }else {
            return mongoTemplate.save(genre);
        }

    }


}
