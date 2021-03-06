package pl.coderslab.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

  List<Comment> findAllByTweetOrderByCreatedDesc(Tweet tweet);

  List<Comment> findAllByTweetIdOrderByCreatedDesc(Long id);
  
  Long countByTweetId(Long id);
}
