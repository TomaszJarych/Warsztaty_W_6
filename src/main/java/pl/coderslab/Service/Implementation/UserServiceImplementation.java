package pl.coderslab.Service.Implementation;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Repository.UserRepository;
import pl.coderslab.Service.UserService;
import pl.coderslab.dto.UserDto;
import pl.coderslab.entity.User;

@Service
public class UserServiceImplementation implements UserService {

  private final UserRepository userRepo;

  @Autowired
  private UserServiceImplementation(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public UserDto findById(Long id) {
    return userRepo.findOne(id).toDto();
  }

  @Override
  public UserDto saveToDB(UserDto dto) {
    return userRepo.save(toUserEntity(dto)).toDto();
  }

  @Override
  public UserDto update(UserDto dto) {
    return userRepo.save(toUserEntity(dto)).toDto();
  }

  @Override
  public void removeFromDB(Long id) {
    userRepo.delete(id);
  }

  @Override
  public Collection<UserDto> findAll() {

    return toUserDtoList(userRepo.findAll());
  }

  @Override
  public Boolean login(UserDto dto) {
    User user = userRepo.findByEmail(dto.getEmail());
    if (Objects.isNull(user)) {
      return false;
    }
    return BCrypt.checkpw(dto.getPassword(), user.getPassword());
  }

  private User toUserEntity(UserDto dto) {
    User user = new User();

    user.setId(dto.getId());
    user.setEnabled(dto.getEnabled());
    user.setEmail(dto.getEmail());
    user.setUsername(dto.getUsername());
    user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));

    return user;
  }

  private List<UserDto> toUserDtoList(Collection<User> users) {
    return users.stream().filter(Objects::nonNull).map(User::toDto).collect(Collectors.toList());
  }
}
