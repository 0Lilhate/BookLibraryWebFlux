package projectJDBC.repository.book;

import org.hibernate.jpa.QueryHints;
import projectJDBC.domain.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Book> getAllBookWithComment() {
        List<Book> books = em.createQuery("SELECT DISTINCT b FROM Book b " +
                                "LEFT JOIN FETCH b.comments bc "
                        , Book.class)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();

        books = em.createQuery("SELECT DISTINCT b from Book b " +
                        "LEFT JOIN FETCH b.genreList bg "+
                        "LEFT JOIN FETCH b.author ba " +
                        "WHERE b in :books", Book.class)
                .setParameter("books", books)
                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
                .getResultList();

        return books;
    }
}
