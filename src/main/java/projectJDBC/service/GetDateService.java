package projectJDBC.service;

import projectJDBC.domain.Book;

public interface GetDateService {
    void insertBook();
    void getAllBook();
    void updateNameBookById();
    void deleteBookById();
    Book getBookById();
}
