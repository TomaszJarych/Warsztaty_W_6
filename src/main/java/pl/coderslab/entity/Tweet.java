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
import pl.coderslab.dto.TweetDto;

@Entity
@Table(name = "tweets")
@Data
@NoArgsConstructor
public class Tweet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;

  private LocalDateTime created = LocalDateTime.now();

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Transient
  public TweetDto toDto() {
    TweetDto dto = new TweetDto();

    dto.setId(getId());
    dto.setText(getText());
    dto.setCreated(getCreated());
 
    dto.setUser(getUser().toSimpleDto());

    return dto;
  }
}
