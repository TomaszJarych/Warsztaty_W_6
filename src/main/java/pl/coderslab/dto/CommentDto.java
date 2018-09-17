package pl.coderslab.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {
	
	
	private Long id;
	
	@NotNull
	private UserDto user;
	
	@NotNull
	private TweetDto tweet;
	
	private LocalDateTime created = LocalDateTime.now();
	
	@NotBlank
	@Size(max=60, message="Maximum number of chars - 60")
	private String text;
}
