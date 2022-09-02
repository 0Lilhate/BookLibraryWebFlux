package projectJDBC.repository.genre;

import projectJDBC.domain.Author;
import projectJDBC.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class GenreRepositoryImpl implements GenreRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre getByNameOrCreate(Genre genre) {
        TypedQuery<Genre> query = em.createQuery("Select g from Genre g WHERE g.name_genre =:name", Genre.class);
        query.setParameter("name", genre.getName_genre());
        List<Genre> genres = query.getResultList();

        if(!genres.isEmpty()){
            return genres.get(0);
        }else {
            return em.merge(genre);
        }
    }
}
