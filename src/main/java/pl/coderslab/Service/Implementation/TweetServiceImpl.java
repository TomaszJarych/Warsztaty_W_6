package pl.coderslab.Service.Implementation;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Repository.TweetRepository;
import pl.coderslab.Repository.UserRepository;
import pl.coderslab.Service.TweetService;
import pl.coderslab.dto.TweetDto;
import pl.coderslab.dto.UserDto;
import pl.coderslab.entity.Tweet;

@Service
public class TweetServiceImpl implements TweetService {

  private final TweetRepository tweetRepository;

  private final UserRepository userRepository;

  @Autowired
  public TweetServiceImpl(TweetRepository tweetRepository, UserRepository userRepository) {
    this.tweetRepository = tweetRepository;
    this.userRepository = userRepository;
  }

  @Override
  public TweetDto findById(Long id) {
    return tweetRepository.findOne(id).toDto();
  }

  @Override
  public TweetDto saveToDB(TweetDto dto) {
    return tweetRepository.save(toTweetEntity(dto)).toDto();
  }

  @Override
  public TweetDto update(TweetDto dto) {
    return tweetRepository.save(toTweetEntity(dto)).toDto();
  }

  @Override
  public void removeFromDB(Long id) {
    tweetRepository.delete(id);
  }

  @Override
  public Collection<TweetDto> findAll() {
    return toTweetDtoList(tweetRepository.findAll());
  }

  @Override
  public Collection<TweetDto> findAllByUser(UserDto user) {

    return toTweetDtoList(tweetRepository.findAllByUser(userRepository.findOne(user.getId())));
  }

  @Override
  public Collection<TweetDto> findAllByUserId(Long id) {
    return toTweetDtoList(tweetRepository.findAllByUserId(id));
  }

  @Override
  public Collection<TweetDto> findAllByOrderByCreatedDesc() {
    return toTweetDtoList(tweetRepository.findAllByOrderByCreatedDesc());
  }

  private Tweet toTweetEntity(TweetDto dto) {
    Tweet tweet;
    if (dto.getId() == null) {
      tweet = new Tweet();
    } else {
      tweet = tweetRepository.findOne(dto.getId());
    }
    tweet.setId(dto.getId());
    tweet.setText(dto.getText());
    tweet.setUser(userRepository.findOne(dto.getUser().getId()));

    return tweet;
  }

  private List<TweetDto> toTweetDtoList(Collection<Tweet> users) {
    return users.stream().filter(Objects::nonNull).map(Tweet::toDto).collect(Collectors.toList());
  }
}
