package pl.coderslab.entity;

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
import pl.coderslab.dto.MessageDto;

@Entity
@Table(name ="app_messages")
@Data
@NoArgsConstructor
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;

  @ManyToOne
  @JoinColumn(name="receiver_id")
  private User receiver;
  
 @ManyToOne
 @JoinColumn(name="sender_id")
  private User sender;

  private Boolean isNewMessage = true;

  @Transient
  public MessageDto toDto() {
    MessageDto dto = new MessageDto();

    dto.setId(getId());
    dto.setText(getText());
    dto.setIsNewMessage(getIsNewMessage());

    dto.setSender(getSender().toDto());
    dto.setReceiver(getReceiver().toDto());

    return dto;
  }
}
