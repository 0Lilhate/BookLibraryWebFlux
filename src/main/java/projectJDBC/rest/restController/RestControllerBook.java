//package projectJDBC.rest.restController;
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import projectJDBC.domain.Book;
//import projectJDBC.dto.BookDto;
//import projectJDBC.repository.book.BookRepository;
//import projectJDBC.service.ParsingDtoBook;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@CrossOrigin
//@RestController
//public class RestControllerBook {
//
////    private final WebService webService;
//    private final ParsingDtoBook parsingDtoBook;
//
//    private final BookRepository bookRepository;
//
//    public RestControllerBook(BookRepository bookRepository, ParsingDtoBook parsingDtoBook) {
//        this.bookRepository = bookRepository;
//        this.parsingDtoBook = parsingDtoBook;
//    }
//
//    @GetMapping("/book/all")
//    public Flux<BookDto> getAllBook(){
////        List<BookDto> bookDtoList = new ArrayList<>();
////        webService.getAllBook().stream().forEach(book -> bookDtoList.add(parsingDtoBook.bookToDtoBook(book)));
////        return new ResponseEntity<>(bookDtoList, HttpStatus.OK);
//        return bookRepository.findAll().map(parsingDtoBook::bookToDtoBook);
//    }
//
//
//    @GetMapping("/book/{id}")
//    public Mono<ResponseEntity<BookDto>> getBook(@PathVariable("id") String id){
////        BookDto bookDto = parsingDtoBook.bookToDtoBook(webService.getBookById(id));
////        return new ResponseEntity<>(bookDto, HttpStatus.OK);
//        return bookRepository.findById(id).map(book -> parsingDtoBook.bookToDtoBook(book))
//                .map(bookDto -> ResponseEntity.ok(bookDto))
//                .onErrorReturn(ResponseEntity.status(404).build())
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//
//    @PutMapping("/book/change")
//    public Mono<ResponseEntity<Boolean>> updateBook(@RequestBody BookDto bookDto){
////        webService.updateNameBookById(bookDto.getId_book(), bookDto.getName());
////        return new ResponseEntity<>(HttpStatus.OK);
//        return bookRepository.updateBookNameById(bookDto.getId_book(), bookDto.getName())
//                .map(ResponseEntity::ok)
//                .onErrorReturn(ResponseEntity.status(404).build());
//
//
//    }
//
//    @DeleteMapping("/book/delete/{id}")
//    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable("id") String id){
////        webService.deleteBookById(id);
////        return new ResponseEntity<>(HttpStatus.OK);
//        return bookRepository.deleteById(id)
//                .map(ResponseEntity::ok)
//                .onErrorReturn(ResponseEntity.status(404).build());
//    }
//
//    @PostMapping(value = "/book/add")
//    public Mono<ResponseEntity<BookDto>>  addBook(@RequestBody BookDto bookDto){
////        Book addBook =  webService.insertBook(parsingDtoBook.bookDtoToBook(bookDto));
////        BookDto addBookDto = parsingDtoBook.bookToDtoBook(addBook);
////        return new ResponseEntity<>(addBookDto, HttpStatus.CREATED);
//
//        return bookRepository.save(parsingDtoBook.bookDtoToBook(bookDto))
//                .map(book -> parsingDtoBook.bookToDtoBook(book))
//                .map(dto -> ResponseEntity.ok(dto))
//                .onErrorReturn(ResponseEntity.status(404).build())
//                .defaultIfEmpty(ResponseEntity.notFound().build());
//    }
//
//
//}
