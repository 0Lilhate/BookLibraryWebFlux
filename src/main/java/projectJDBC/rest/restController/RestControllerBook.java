package projectJDBC.rest.restController;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projectJDBC.domain.Book;
import projectJDBC.dto.BookDto;
import projectJDBC.service.ParsingDtoBook;
import projectJDBC.service.WebService;


import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class RestControllerBook {

    private final WebService webService;
    private final ParsingDtoBook parsingDtoBook;

    public RestControllerBook(WebService webService, ParsingDtoBook parsingDtoBook) {
        this.webService = webService;
        this.parsingDtoBook = parsingDtoBook;
    }

    @GetMapping("/book/all")
    public ResponseEntity<List<BookDto>> getAllBook(){
        List<BookDto> bookDtoList = new ArrayList<>();
        webService.getAllBook().stream().forEach(book -> bookDtoList.add(parsingDtoBook.bookToDtoBook(book)));

        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
    }


    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable("id") String id){
        BookDto bookDto = parsingDtoBook.bookToDtoBook(webService.getBookById(id));
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @PutMapping("/book/change")
    public ResponseEntity<?> updateBook(@RequestBody BookDto bookDto){
        webService.updateNameBookById(bookDto.getId_book(), bookDto.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable("id") String id){
        webService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/book/add")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto){
        Book addBook =  webService.insertBook(parsingDtoBook.bookDtoToBook(bookDto));
        BookDto addBookDto = parsingDtoBook.bookToDtoBook(addBook);
        return new ResponseEntity<>(addBookDto, HttpStatus.CREATED);
    }


}
