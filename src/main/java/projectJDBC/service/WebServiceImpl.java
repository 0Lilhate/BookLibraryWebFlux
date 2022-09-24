package projectJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJDBC.domain.Book;
import projectJDBC.dto.BookDto;
import projectJDBC.repository.book.BookRepository;

import java.util.List;

@Service
public class WebServiceImpl implements WebService{

    private final BookRepository bookRepository;
    private final ParsingDtoBook parsingDtoBook;

    @Autowired
    public WebServiceImpl(BookRepository bookRepository, ParsingDtoBook parsingDtoBook) {
        this.bookRepository = bookRepository;
        this.parsingDtoBook = parsingDtoBook;
    }

    @Override
    public BookDto insertBook(Book book) {

        return parsingDtoBook.bookToDtoBook(book);
    }

    @Override
    public List<BookDto> getAllBook() {
//        return bookRepository.findAll();
        return null;
    }

    @Override
    public void updateNameBookById(String id, String name) {
//       bookRepository.updateBookNameById(id,name);
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);

    }

    @Override
    public BookDto getBookById(String id) {
//        return bookRepository.findById(id).orElseThrow(NotFoundException::new);
        return null;
    }
}
