package projectJDBC.repository.book;

import projectJDBC.domain.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> getAllBookWithComment();

}
