package projectJDBC.service;

import projectJDBC.domain.Book;

import java.util.List;

public interface WebService {
    Book insertBook(Book book);
    List<Book> getAllBook();
    void updateNameBookById(String id, String name);
    void deleteBookById(String id);
    Book getBookById(String id);


}
