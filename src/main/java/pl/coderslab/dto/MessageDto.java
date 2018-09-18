package pl.coderslab.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {

  private Long id;

  @NotBlank(message="Can't send empty message!")
  private String text;

  @NotNull
  private UserDto receiver;

  @NotNull
  private UserDto sender;

  private Boolean isNewMessage = true;

  public String getIsNewString() {
    return (isNewMessage) ? "No" : "Yes";
  }
}
