package pl.coderslab.Service.Implementation;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.hibernate.validator.internal.engine.messageinterpolation.parser.TokenCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.Repository.MessageRepository;
import pl.coderslab.Repository.UserRepository;
import pl.coderslab.Service.MessageService;
import pl.coderslab.dto.MessageDto;
import pl.coderslab.dto.UserDto;
import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;

@Service
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private final UserRepository userRepository;

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository) {
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
  }

  @Override
  public MessageDto findById(Long id) { 
    return messageRepository.findOne(id).toDto();
  }

  @Override
  public MessageDto saveToDB(MessageDto dto) { 
    return messageRepository.save(toMessageEntity(dto)).toDto();
  }

  @Override
  public MessageDto update(MessageDto dto) { 
    return messageRepository.save(toMessageEntity(dto)).toDto();
  }

  @Override
  public void removeFromDB(Long id) { 
	  messageRepository.delete(id);
  }

  @Override
  public Collection<MessageDto> findAll() { 
    return toMessageDtoList(messageRepository.findAll());
  }

  @Override
  public List<MessageDto> findAllByReceiver(UserDto reciver) { 
	  User user = userRepository.findOne(reciver.getId());
    return toMessageDtoList(messageRepository.findAllByReceiver(user));
  }

  @Override
  public List<MessageDto> findAllByReceiverId(Long id) { 
    return toMessageDtoList(messageRepository.findAllByReceiverId(id));
  }

  @Override
  public List<MessageDto> findAllBySender(UserDto sender) { 
	  User user = userRepository.findOne(sender.getId());
    return toMessageDtoList(messageRepository.findAllBySender(user));
  }

  @Override
  public List<MessageDto> findAllBySenderId(Long id) { 
    return toMessageDtoList(messageRepository.findAllBySenderId(id));
  }

  private Message toMessageEntity(MessageDto dto) {
    Message message;
    if (dto.getId() == null) {
      message = new Message();
    } else {
      message = messageRepository.findOne(dto.getId());
    }

    message.setId(dto.getId());
    message.setText(dto.getText());
    message.setIsNewMessage(dto.getIsNewMessage());
    message.setSender(userRepository.findOne(dto.getSender().getId()));
    message.setReceiver(userRepository.findOne(dto.getReceiver().getId()));

    return message;
  }

  private List<MessageDto> toMessageDtoList(List<Message> list) {
    return list.stream().filter(Objects::nonNull).map(Message::toDto).collect(Collectors.toList());
  }
}
