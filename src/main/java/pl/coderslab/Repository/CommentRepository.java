package pl.coderslab.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;

public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByTweetOrderByCreatedDesc(Tweet tweet);

  List<Comment> findAllByTweetIdOrderByCreatedDesc(Long id);
  
  Long countByTweetId(Long id);
}
