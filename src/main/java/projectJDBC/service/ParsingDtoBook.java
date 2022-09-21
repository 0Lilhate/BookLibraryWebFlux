package projectJDBC.service;

import projectJDBC.domain.Book;
import projectJDBC.dto.BookDto;

public interface ParsingDtoBook {
    BookDto bookToDtoBook(Book book);
    Book bookDtoToBook(BookDto bookDto);
}
