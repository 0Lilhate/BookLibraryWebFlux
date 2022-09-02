package projectJDBC.repository.author;

import projectJDBC.domain.Author;

public interface AuthorRepositoryCustom {
    Author getByNameOrCreate(Author author);
}
