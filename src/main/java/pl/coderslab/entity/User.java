package pl.coderslab.entity;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
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

  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
  @EqualsAndHashCode.Exclude
  private Set<Tweet> tweets = new LinkedHashSet<>();

  @Column(unique = true)
  private String email;

  @Transient
  public UserDto toDto() {
    UserDto dto = new UserDto();
    dto.setId(getId());
    dto.setUsername(getUsername());
    dto.setEnabled(getEnabled());
    dto.setEmail(getEmail());
    dto.setPassword(getPassword());
    if (Objects.nonNull(getTweets()) && !getTweets().isEmpty()) {
      dto.getTweets().clear();
      getTweets()
          .stream()
          .filter(Objects::nonNull)
          .map(Tweet::toDto)
          .forEach(el -> dto.getTweets().add(el));
    }

    return dto;
  }

  @Transient
  public UserDto toSimpleDto() {
    UserDto dto = new UserDto();
    dto.setId(getId());
    dto.setUsername(getUsername());
    dto.setEnabled(getEnabled());
    dto.setEmail(getEmail());
    dto.setPassword(getPassword());

    return dto;
  }
}
