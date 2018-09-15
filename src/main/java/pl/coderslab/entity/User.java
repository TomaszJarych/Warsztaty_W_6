package pl.coderslab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.dto.UserDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String username;

  private String password;

  private Boolean enabled;

  @Column(unique = true)
  private String email;

  @Transient
  public UserDto toDto() {
    UserDto dto = new UserDto();
    dto.setId(getId());
    dto.setUsername(getUsername());
    dto.setEnabled(getEnabled());
    dto.setEmail(getEmail());

    return dto;
  }
}
