package projectJDBC.service;

import projectJDBC.domain.Book;
import projectJDBC.dto.BookDto;

import java.util.List;

public interface WebService {
    BookDto insertBook(Book book);
    List<BookDto> getAllBook();
    void updateNameBookById(String id, String name);
    void deleteBookById(String id);
    BookDto getBookById(String id);


}
