package projectJDBC.dao.genre;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import projectJDBC.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJDBC implements GenreDao{

    @PersistenceContext
    EntityManager em;

    public GenreDaoJDBC(EntityManager entityManager) {
        em = entityManager;
    }

    @Override
    public List<Genre> getAllGenre() {
        return null;
    }

    @Override
    public void updateGenre(Genre genre) {

    }

    @Override
    public void deleteByIdGenre(long id) {
        //Map<String, Object> params = Collections.singletonMap("id", id);
//        namedParameterJdbcOperations.update(
//                "delete from genre where id = :id", Map.of("id", id)
//        );
    }

    @Override
    public void insertGenre(Genre genre) {
//        Map<String, Object> param = Map.of("id", genre.getId_genre(), "name", genre.getName_genre());
//
//        namedParameterJdbcOperations.update("insert into genre (id, GENRE) " +
//                "values (:id, :name)", param);
    }

    @Override
    public Genre getByNameOrCreate(Genre genre) {
        TypedQuery<Genre> query = em.createQuery("Select g from Genre g WHERE g.name = :name", Genre.class);
        query.setParameter("name", genre.getName_genre());
        List<Genre> genres = query.getResultList();

        if (!genres.isEmpty()) {
            return genres.get(0);
        } else {
            return em.merge(genre);
        }
    }
}
