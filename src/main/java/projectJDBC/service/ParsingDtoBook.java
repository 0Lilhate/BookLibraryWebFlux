package projectJDBC.service;

import projectJDBC.domain.Book;
import projectJDBC.dto.BookDto;

public interface ParsingDtoBook {
    BookDto Book_to_DtoBook(Book book);
    Book BookDto_to_Book(BookDto bookDto);
}
