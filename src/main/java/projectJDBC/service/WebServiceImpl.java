package projectJDBC.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projectJDBC.domain.Book;
import projectJDBC.repository.book.BookRepository;
import projectJDBC.rest.NotFoundException;

import java.util.List;

@Service
public class WebServiceImpl implements WebService{

    private final BookRepository bookRepository;

    @Autowired
    public WebServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book insertBook(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public void updateNameBookById(String id, String name) {
       bookRepository.updateBookNameByName(id,name);
    }

    @Override
    public void deleteBookById(String id) {
        bookRepository.deleteById(id);

    }

    @Override
    public Book getBookById(String id) {
        return bookRepository.findById(id).orElseThrow(NotFoundException::new);
    }
}
