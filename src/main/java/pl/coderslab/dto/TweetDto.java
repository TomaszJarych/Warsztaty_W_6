package pl.coderslab.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TweetDto {

  private Long id;

  @Size(max = 140, message = "Tweet must have maximum 140 letters")
  @NotBlank(message = "Tweet can't be empty")
  private String text;

  private LocalDateTime created;

  private UserDto user;

  public String getFullDate() {
	  
    return this.created.format(DateTimeFormatter.ofPattern("d MMM uuuu  HH:mm:ss"));
  }
  
}
