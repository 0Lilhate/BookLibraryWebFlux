package projectJDBC.repository.book;


import projectJDBC.domain.Book;
import reactor.core.publisher.Flux;

import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom{


//    @Autowired
//    private ReactiveMongoTemplate reactiveMongoTemplate;


    @Override
    public Flux<Book> findBookByName(String bookName) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("name_book").is(bookName));
//        return reactiveMongoTemplate.find(query,Book.class);
        return null;
    }



}
