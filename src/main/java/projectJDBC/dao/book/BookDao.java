package projectJDBC.dao.book;

import projectJDBC.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao  {
    Book insert(Book book);
    Optional<Book> getByIdBook(long id);
    void deleteById(long id);
    void updateNameByID(long id, String name);
    List<Book> getAll();

}
