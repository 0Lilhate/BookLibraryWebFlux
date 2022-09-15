package projectJDBC.rest;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import projectJDBC.domain.Author;
import projectJDBC.domain.Book;
import projectJDBC.domain.Genre;
import projectJDBC.dto.BookDto;
import projectJDBC.service.ParsingDtoBook;
import projectJDBC.service.WebService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)

class BookControllerTest {
    private static final String ID_BOOK1 = "1";
    private static final String BOOK_NAME1 = "Eva";
    private static final String BOOK_YEAR1 = "2000-01-01";
    private static final String GENRE_NAME1 = "Drama";
    private static final String NAME_AUTHOR1 = "Pushkin";
    private static final String YEAR_AUTHOR1 = "1899-05-22";

    private static final String ID_BOOK2 = "2";
    private static final String BOOK_NAME2 = "Anime";
    private static final String BOOK_YEAR2 = "2005-01-01";
    private static final String GENRE_NAME2 = "Horror";
    private static final String NAME_AUTHOR2 = "Kioto";
    private static final String YEAR_AUTHOR2 = "1898-11-26";


    private MockMvc mockMvc;
    @MockBean
    private WebService webService;
    @MockBean
    private ParsingDtoBook parsingDtoBook;
    @Autowired
    private WebApplicationContext webApplicationContext;


    @BeforeEach
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    void listPage() throws Exception {
        when(webService.getAllBook()).thenReturn(List.of(new Book(ID_BOOK1,BOOK_NAME1, BOOK_YEAR1,
                        new Genre(GENRE_NAME1), new Author(NAME_AUTHOR1, YEAR_AUTHOR1), null)));

        when(parsingDtoBook.Book_to_DtoBook(any())).thenReturn(new BookDto(ID_BOOK1, BOOK_NAME1, BOOK_YEAR1, NAME_AUTHOR1,
                YEAR_AUTHOR1, Collections.singletonList(GENRE_NAME1),null));

        List<BookDto> bookDtoList = new ArrayList<>();
        bookDtoList.add(parsingDtoBook.Book_to_DtoBook(any()));

        mockMvc.perform(get("/book")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("books", bookDtoList))
                .andExpect(view().name("listBook"));

    }

    @Test
    void editBook() throws Exception {
        val bookDto = new BookDto(ID_BOOK1, BOOK_NAME1, BOOK_YEAR1, NAME_AUTHOR1,
                YEAR_AUTHOR1, Collections.singletonList(GENRE_NAME1), Collections.singletonList(GENRE_NAME1));
        val book = new Book(ID_BOOK1,BOOK_NAME1, BOOK_YEAR1,
                new Genre(GENRE_NAME1), new Author(NAME_AUTHOR1, YEAR_AUTHOR1), null);

        when(webService.getBookById(any())).thenReturn(book);
        when(parsingDtoBook.Book_to_DtoBook(any())).thenReturn(bookDto);

        mockMvc.perform(get("/book/edit").param("id", ID_BOOK1)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", bookDto))
                .andExpect(view().name("edit"));

    }

    @Test
    void updateBook() throws Exception {
        val bookDto = new BookDto(ID_BOOK1, BOOK_NAME1, BOOK_YEAR1, NAME_AUTHOR1,
                YEAR_AUTHOR1, Collections.singletonList(GENRE_NAME1), Collections.singletonList(GENRE_NAME1));

        mockMvc.perform(post("/book/edit", bookDto)).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book"));
    }

    @Test
    void deleteBook() throws Exception {

        mockMvc.perform(post("/book/edit/delete", ID_BOOK1).param("id", ID_BOOK1)).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book"));

    }

    @Test
    void createBook() throws Exception {
        val bookDto = new BookDto(ID_BOOK1, BOOK_NAME1, BOOK_YEAR1, NAME_AUTHOR1,
                YEAR_AUTHOR1, Collections.singletonList(GENRE_NAME1), null);

        val book = new Book(ID_BOOK1,BOOK_NAME1, BOOK_YEAR1,
                new Genre(GENRE_NAME1), new Author(NAME_AUTHOR1, YEAR_AUTHOR1), null);



//        given(parsingDtoBook.BookDto_to_Book(bookDto)).willReturn(book);
        when(parsingDtoBook.BookDto_to_Book(any())).thenReturn(book);

        when(webService.insertBook(any()))
                .thenReturn(book);

//        assertThat(parsingDtoBook.BookDto_to_Book(any())).isNotEqualTo(book);


        mockMvc.perform(post("/book/create", bookDto)
                    .flashAttr("bookDto", bookDto)
                ).andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/book"));
    }

    @Test
    void getHtmlCreateBook() throws Exception {

        mockMvc.perform(get("/book/create")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attribute("book", new BookDto()))
                .andExpect(view().name("createBook"));

    }
}