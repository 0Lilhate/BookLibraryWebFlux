package projectJDBC.repository.book;

import projectJDBC.domain.Book;
import reactor.core.publisher.Flux;


import java.util.List;

public interface BookRepositoryCustom {

    Flux<Book> findBookByName(String bookName);




}
