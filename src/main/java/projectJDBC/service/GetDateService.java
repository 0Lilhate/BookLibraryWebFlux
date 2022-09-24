package projectJDBC.service;

import projectJDBC.domain.Author;
import projectJDBC.domain.Book;

import java.util.concurrent.ExecutionException;

public interface GetDateService {
    Book insertBook();
    void getAllBook();
    void updateNameBookById();
    void deleteBookById();
    boolean getBookById();

    Author test() throws ExecutionException, InterruptedException;
}
