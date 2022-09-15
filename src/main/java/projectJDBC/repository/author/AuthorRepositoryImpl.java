package projectJDBC.repository.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import projectJDBC.domain.Author;




public class AuthorRepositoryImpl implements AuthorRepositoryCustom   {



    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Author getByNameOrCreate(Author author) {
//        TypedQuery<Author> query = em.createQuery("Select a from Author a WHERE a.authorName =:name", Author.class);
//        query.setParameter("name", author.getAuthorName());
//        List<Author> authors = query.getResultList();

        Query queryMongo = new Query();
        queryMongo.addCriteria(Criteria.where("author_name").is(author.getAuthorName()));
        Author authorTemp = mongoTemplate.findOne(queryMongo, Author.class);


        if (authorTemp != null){
            return authorTemp;
        }else {
            return mongoTemplate.save(author);
        }

    }
}
