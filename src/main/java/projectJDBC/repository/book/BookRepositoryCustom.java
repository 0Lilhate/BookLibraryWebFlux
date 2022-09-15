package projectJDBC.repository.book;

import projectJDBC.domain.Book;

import java.util.List;

public interface BookRepositoryCustom {
    List<Book> getAllBookWithComment();
    List<Book> findBookByName(String bookName);

    boolean deleteBookByName(String bookName);
    Boolean updateBookNameByName(String bookName, String newBookName);


}
