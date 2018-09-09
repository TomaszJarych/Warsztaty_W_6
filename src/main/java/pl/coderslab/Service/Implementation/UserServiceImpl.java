package pl.coderslab.Service.Implementation;

import java.util.Base64;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Dto.UserDto;
import pl.coderslab.Entity.User;
import pl.coderslab.Repository.UserRepository;
import pl.coderslab.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDto register(UserDto dto) {
		User user = userRepository.save(createUserFromDto(dto));
		return getDtoFromUser(user);
	}

	@Override
	public Boolean login(UserDto dto) {
		User user = userRepository.findByEmail(dto.getEmail());
		if (Objects.isNull(user)) {
			return false;
		}
		String password = Base64.getEncoder()
				.encodeToString(dto.getPassword().getBytes());
		return user.getPassword().equals(password);
	}

	private User createUserFromDto(UserDto dto) {
		User user = new User();

		user.setEmail(dto.getEmail());
		user.setEnabled(true);
		user.setUserName(dto.getUserName());
		user.setPassword(Base64.getEncoder()
				.encodeToString(dto.getPassword().getBytes()));

		return user;
	}
	private UserDto getDtoFromUser(User user) {

		UserDto dto = new UserDto();

		dto.setId(user.getId());
		dto.setEmail(user.getEmail());
		dto.setUserName(user.getUserName());
		dto.setEnabled(user.getEnabled());

		return dto;

	}

}
