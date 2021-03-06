package pl.coderslab.Service;

import java.util.Collection;

import pl.coderslab.dto.TweetDto;
import pl.coderslab.dto.UserDto;
import pl.coderslab.entity.Tweet;

public interface TweetService extends BaseCrudService<TweetDto, Long> {
	
	Collection<TweetDto> findAllByUser(UserDto user);
	Collection<TweetDto> findAllByUserId(Long id);
	
	Collection<TweetDto> findAllByOrderByCreatedDesc();
}
