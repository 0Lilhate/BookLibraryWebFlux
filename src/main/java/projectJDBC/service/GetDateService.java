package projectJDBC.service;

import projectJDBC.domain.Book;

public interface GetDateService {
    Book insertBook();
    void getAllBook();
    void updateNameBookById();
    void deleteBookById();
    boolean getBookById();

    void test();
}
