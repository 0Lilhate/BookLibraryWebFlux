package projectJDBC.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import projectJDBC.domain.Book;

public interface BookRepository extends MongoRepository<Book, String>, BookRepositoryCustom {

//    @Transactional
//    @Modifying
//    @Query("UPDATE Book b set b.name=:name WHERE b.id_book=:id")
//    void updateNameById(@Param("id") Long id, @Param("name")String name);


    @Override
    Book save(Book entity);
}
