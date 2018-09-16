package pl.coderslab.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.entity.Tweet;
import pl.coderslab.entity.User;

public interface TweetRepository extends JpaRepository<Tweet, Long>{
	
	Collection<Tweet> findAllByUser(User user);
	Collection<Tweet> findAllByUserId(Long id);
}
