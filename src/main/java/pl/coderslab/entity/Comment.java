package pl.coderslab.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.dto.CommentDto;

@Entity
@Table(name="tweet_comments")
@NoArgsConstructor
@Data
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="tweet_id")
	private Tweet tweet;
	
	private LocalDateTime created = LocalDateTime.now();
	
	private String text;
	
	
	@Transient
	public CommentDto toDto() {
		CommentDto dto = new CommentDto();
		
		dto.setId(getId());
		dto.setText(getText());
		dto.setCreated(getCreated());
		
		dto.setUser(getUser().toSimpleDto());
		dto.setTweet(getTweet().toDto());
		
		return dto;
	}
}
