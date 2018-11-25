package pl.mb.dao;

import org.springframework.data.repository.CrudRepository;
import pl.mb.model.Comment;

public interface CommentDao extends CrudRepository<Comment, Long> {
}
