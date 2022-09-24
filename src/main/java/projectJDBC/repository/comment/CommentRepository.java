package projectJDBC.repository.comment;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import projectJDBC.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment, String> {
}
