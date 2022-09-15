package projectJDBC.repository.book;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import projectJDBC.domain.Book;

import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom{


    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Book> getAllBookWithComment() {

//        List<Book> books = em.createQuery("SELECT DISTINCT b FROM Book b " +
//                                "LEFT JOIN FETCH b.comments bc "
//                        , Book.class)
//                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
//                .getResultList();
//
//        books = em.createQuery("SELECT DISTINCT b from Book b " +
//                        "LEFT JOIN FETCH b.genreList bg "+
//                        "LEFT JOIN FETCH b.author ba " +
//                        "WHERE b in :books", Book.class)
//                .setParameter("books", books)
//                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
//                .getResultList();
//
//        return books;

        return null;
    }

    @Override
    public List<Book> findBookByName(String bookName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name_book").is(bookName));
        return mongoTemplate.find(query,Book.class);
    }

    @Override
    public boolean deleteBookByName(String bookName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name_book").is(bookName));
        DeleteResult res =mongoTemplate.remove(query, Book.class);
        return res.wasAcknowledged();
    }

    @Override
    public Boolean updateBookNameByName(String id, String newBookName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();
        update.set("name_book", newBookName);
        UpdateResult result = mongoTemplate.updateFirst(query, update,Book.class);
        return result.wasAcknowledged();
    }


}
