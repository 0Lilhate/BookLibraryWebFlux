package projectJDBC.repository.book;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import projectJDBC.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String>, BookRepositoryCustom {



}
