package pl.coderslab.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.validator.IsUniqueEmail;
import pl.coderslab.validator.IsUniqueUsername;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

  private Long id;

  @NotBlank @IsUniqueUsername private String username;

  @NotBlank private String password;

  private Boolean enabled;

  @NotBlank @IsUniqueEmail @Email private String email;
}
