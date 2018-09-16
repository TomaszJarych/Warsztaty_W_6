package pl.coderslab.Service;

import pl.coderslab.dto.UserDto;

public interface UserService extends BaseCrudService<UserDto, Long> {

  UserDto login(UserDto dto);
}
