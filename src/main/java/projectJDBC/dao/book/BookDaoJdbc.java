package projectJDBC.dao.book;

import lombok.RequiredArgsConstructor;
import org.hibernate.jpa.QueryHints;

import org.springframework.stereotype.Repository;


import projectJDBC.domain.Book;
import projectJDBC.domain.Comment;


import javax.persistence.*;
import javax.persistence.PersistenceContext;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {


    @PersistenceContext
    private EntityManager em;



    @Override
    public Book insert(Book book) {

        if (book.getId_book() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }

    }

    @Override
    public Optional<Book> getByIdBook(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id_book=:id");
        query.setParameter("id", id);
        query.executeUpdate();

    }



    @Override
    public void updateNameByID(long id, String name) {
        Query query = em.createQuery("update Book b " +
                "set b.name=:name " +
                "WHERE b.id_book=:id");
        query.setParameter("id", id);
        query.setParameter("name", name);
        query.executeUpdate();
    }

    @Override
    public List<Book> getAll() {
//        TypedQuery<Book> query = em.createQuery("select b from Book b " +
//                "join fetch b.genreList g join fetch b.author a", Book.class);
//        query.setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false);
//        List<Book> bookList = em.createQuery("select b from Book b " +
//                "join fetch b.genreList g join fetch b.author a", Book.class)
//                .setHint(QueryHints.HINT_PASS_DISTINCT_THROUGH, false)
//                .getResultList();
//
//        TypedQuery<Comment> commentTypedQuery = em.createQuery("select c from Comment c", Comment.class);
//        mergeBookInfo(bookList, commentTypedQuery.getResultList());


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



    private void mergeBookInfo(List<Book> books, List<Comment> comments) {
//        if(books == null || comments == null){
//            return;
//        }
//        for (Book tempBook: books) {
//            for (int j = 0; j<comments.size(); j++){
//                if(tempBook.getId_book() == comments.get(j).getBook().getId_book()){
//                    tempBook.setComments(comments);
//                }else{
//                    List<Comment> c = new ArrayList<>();
//                    c.add(new Comment());
//                    tempBook.setComments(c);
//                }
//            }
//        }

    }






}
