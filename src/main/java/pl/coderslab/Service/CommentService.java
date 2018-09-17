package pl.coderslab.Service;

import java.util.List;

import pl.coderslab.dto.CommentDto;
import pl.coderslab.dto.TweetDto;

public interface CommentService extends BaseCrudService<CommentDto, Long> {
	
	  List<CommentDto> findAllByTweetOrderByCreatedDesc(TweetDto tweet);

	  List<CommentDto> findAllByTweetIdOrderByCreatedDesc(Long id);
}
