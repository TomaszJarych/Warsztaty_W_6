package pl.coderslab.Dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import pl.coderslab.Validator.EmailUnique;

@Data
public class UserDto {

	private Long id;

	@NotBlank
	private String userName;

	@NotBlank
	private String password;

	private Boolean enabled;

	@Email
	@EmailUnique
	private String email;
}
