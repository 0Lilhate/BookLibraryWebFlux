//package projectJDBC.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import projectJDBC.dto.BookDto;
//import projectJDBC.repository.book.BookRepository;
//import projectJDBC.service.ParsingDtoBook;
//
//public class ReactBookController {
//
////    private final WebService webService;
//    private final BookRepository bookRepository;
//    private final ParsingDtoBook parsingDtoBook;
//
//    @Autowired
//    public ReactBookController(BookRepository bookRepository, ParsingDtoBook parsingDtoBook) {
//        this.bookRepository = bookRepository;
//        this.parsingDtoBook = parsingDtoBook;
//    }
//
//    @GetMapping("/book")
//    public String listPage(Model model) {
////        List<BookDto> bookDtoList = new ArrayList<>();
////        for (val tempBook : webService.getAllBook()) {
////            bookDtoList.add(parsingDtoBook.bookToDtoBook(tempBook));
////        }
////
////        model.addAttribute("books", bookDtoList);
////        return "listBook";
//
//        model.addAttribute("books",bookRepository.findAll().map(parsingDtoBook::bookToDtoBook));
//        return "listBook";
//    }
//
//    @GetMapping("/book/edit")
//    public String editBook(@RequestParam("id") String id, Model model){
////        BookDto bookDto = parsingDtoBook.bookToDtoBook(webService.getBookById(id));
////        model.addAttribute("book", bookDto);
//        model.addAttribute("book", bookRepository.findById(id).map(parsingDtoBook::bookToDtoBook));
//        return "edit";
//    }
//
//    @RequestMapping(value = "/book/edit", method = RequestMethod.POST)
//    public String updateBook(Model model, @ModelAttribute("bookDto") BookDto bookDto){
////        webService.updateNameBookById(bookDto.getId_book(), bookDto.getName());
//        bookRepository.updateBookNameById(bookDto.getId_book(),bookDto.getName());
//        return "redirect:/book";
//    }
//
//    @RequestMapping(path = "/book/edit/delete", method = RequestMethod.POST)
//    public String deleteBook(@RequestParam("id") String id){
////        webService.deleteBookById(id);
//        bookRepository.deleteById(id);
//
//        return "redirect:/book";
//    }
//
//    @RequestMapping(value = "/book/create", method = RequestMethod.POST)
//    public String createBook(@ModelAttribute BookDto bookDto) {
////        webService.insertBook(parsingDtoBook.bookDtoToBook(bookDto));
//        bookRepository.save(parsingDtoBook.bookDtoToBook(bookDto));
//        return "redirect:/book";
//    }
//
//    @GetMapping("/book/create")
//    public String getHtmlCreateBook(Model model){
//        model.addAttribute("book", new BookDto());
//        return "createBook";
//    }
//
//}
