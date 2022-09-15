package projectJDBC.rest;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import projectJDBC.dto.BookDto;
import projectJDBC.service.ParsingDtoBook;
import projectJDBC.service.ParsingDtoImpl;
import projectJDBC.service.WebService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private final WebService webService;
    private ParsingDtoBook parsingDtoBook;

    @Autowired
    public BookController(WebService webService, ParsingDtoBook parsingDtoBook) {
        this.webService = webService;
        this.parsingDtoBook = parsingDtoBook;
    }

    @GetMapping("/book")
    public String listPage(Model model) {
        List<BookDto> bookDtoList = new ArrayList<>();
        for (val tempBook : webService.getAllBook()) {
            bookDtoList.add(parsingDtoBook.Book_to_DtoBook(tempBook));
        }

        model.addAttribute("books", bookDtoList);
        return "listBook";
    }

    @GetMapping("/book/edit")
    public String editBook(@RequestParam("id") String id, Model model){
        BookDto bookDto = parsingDtoBook.Book_to_DtoBook(webService.getBookById(id));
        model.addAttribute("book", bookDto);
        return "edit";
    }

    @RequestMapping(value = "/book/edit", method = RequestMethod.POST)
    public String updateBook(Model model, @ModelAttribute("bookDto") BookDto bookDto){
        webService.updateNameBookById(bookDto.getId_book(), bookDto.getName());
        return "redirect:/book";
    }

    @RequestMapping(path = "/book/edit/delete", method = RequestMethod.POST)
    public String deleteBook(@RequestParam("id") String id){
        webService.deleteBookById(id);

        return "redirect:/book";
    }

    @RequestMapping(value = "/book/create", method = RequestMethod.POST)
    public String createBook(@ModelAttribute BookDto bookDto) {
        webService.insertBook(parsingDtoBook.BookDto_to_Book(bookDto));
        return "redirect:/book";
    }

    @GetMapping("/book/create")
    public String getHtmlCreateBook(Model model){
        model.addAttribute("book", new BookDto());
        return "createBook";
    }

}
