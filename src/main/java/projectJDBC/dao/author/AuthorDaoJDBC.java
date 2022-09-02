package projectJDBC.dao.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import projectJDBC.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJDBC implements AuthorDao{

    private final EntityManager em;


    @Override
    public List<Author> getAllAuthor() {
        TypedQuery<Author> query = em.createQuery("select a FROM Author a", Author.class);
        return query.getResultList();
    }

    @Override
    public void updateAuthor(long id, String name_author) {
        Query query = em.createQuery("UPDATE Author a set a.authorName =: name " +
                "WHERE a.id_author =: id");
        query.setParameter("name", name_author);
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void deleteByIdAuthor(long id) {
        Query query = em.createQuery("delete from Author b where b.id_author=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Author insertAuthor(Author author) {
        if (author.getId_author() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> getByIdAuthor(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }
}
