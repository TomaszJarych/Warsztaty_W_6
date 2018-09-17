package pl.coderslab.Service.Implementation;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Repository.CommentRepository;
import pl.coderslab.Repository.TweetRepository;
import pl.coderslab.Repository.UserRepository;
import pl.coderslab.Service.CommentService;
import pl.coderslab.dto.CommentDto;
import pl.coderslab.dto.TweetDto;
import pl.coderslab.entity.Comment;
import pl.coderslab.entity.Tweet;

@Service
public class CommentServiceImpl implements CommentService {

  private final CommentRepository commentRepository;
  private final UserRepository userRepository;
  private final TweetRepository tweetRepository;

  @Autowired
  public CommentServiceImpl(
      CommentRepository commentRepository,
      UserRepository userRepository,
      TweetRepository tweetRepository) {
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
    this.tweetRepository = tweetRepository;
  }

  @Override
  public CommentDto findById(Long id) {
    return commentRepository.findOne(id).toDto();
  }

  @Override
  public CommentDto saveToDB(CommentDto dto) {
    return commentRepository.save(toCommentEntity(dto)).toDto();
  }

  @Override
  public CommentDto update(CommentDto dto) {
    return commentRepository.save(toCommentEntity(dto)).toDto();
  }

  @Override
  public void removeFromDB(Long id) {
    commentRepository.delete(id);
  }

  @Override
  public Collection<CommentDto> findAll() {
    return toCommentDtoList(commentRepository.findAll());
  }

  @Override
  public List<CommentDto> findAllByTweetOrderByCreatedDesc(TweetDto tweet) {
    Tweet tweetToFind = tweetRepository.findOne(tweet.getId());
    return toCommentDtoList(commentRepository.findAllByTweetOrderByCreatedDesc(tweetToFind));
  }

  @Override
  public List<CommentDto> findAllByTweetIdOrderByCreatedDesc(Long id) {
    return toCommentDtoList(commentRepository.findAllByTweetIdOrderByCreatedDesc(id));
  }

  @Override
  public Long countByTweetId(Long id) { 
    return commentRepository.countByTweetId(id);
  }

  private Comment toCommentEntity(CommentDto dto) {
    Comment comment;

    if (dto.getId() == null) {
      comment = new Comment();
    } else {
      comment = commentRepository.findOne(dto.getId());
    }

    comment.setId(dto.getId());
    comment.setText(dto.getText());

    comment.setUser(userRepository.findOne(dto.getUser().getId()));
    comment.setTweet(tweetRepository.findOne(dto.getTweet().getId()));

    return comment;
  }

  private List<CommentDto> toCommentDtoList(Collection<Comment> comments) {
    return comments
        .stream()
        .filter(Objects::nonNull)
        .map(Comment::toDto)
        .collect(Collectors.toList());
  }
}
