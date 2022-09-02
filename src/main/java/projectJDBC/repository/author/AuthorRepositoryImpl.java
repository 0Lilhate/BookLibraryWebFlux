package projectJDBC.repository.author;

import projectJDBC.domain.Author;
import projectJDBC.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


public class AuthorRepositoryImpl implements AuthorRepositoryCustom   {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author getByNameOrCreate(Author author) {
        TypedQuery<Author> query = em.createQuery("Select a from Author a WHERE a.authorName =:name", Author.class);
        query.setParameter("name", author.getAuthorName());
        List<Author> authors = query.getResultList();

        if (!authors.isEmpty()){
            return authors.get(0);
        }else {
            return em.merge(author);
        }

    }
}
