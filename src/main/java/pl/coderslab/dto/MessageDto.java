package pl.coderslab.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageDto {

  private Long id;

  private String text;

  private UserDto receiver;

  private UserDto sender;

  private Boolean isNewMessage = true;

  public String getIsNewString() {
    return (isNewMessage) ? "Yes" : "No";
  }
}
