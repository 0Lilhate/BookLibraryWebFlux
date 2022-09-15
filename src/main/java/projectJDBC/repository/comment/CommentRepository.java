package projectJDBC.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import projectJDBC.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
