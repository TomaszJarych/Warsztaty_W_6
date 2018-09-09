package pl.coderslab.Service;

import pl.coderslab.Dto.UserDto;

public interface UserService{

	UserDto register(UserDto dto);

	Boolean login(UserDto dto);
}
