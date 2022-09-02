package projectJDBC.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import projectJDBC.domain.Comment;

interface CommentRepository extends JpaRepository<Comment, Long> {
}
