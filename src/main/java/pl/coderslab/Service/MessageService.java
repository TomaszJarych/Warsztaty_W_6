package pl.coderslab.Service;

import java.util.List;

import pl.coderslab.dto.MessageDto;
import pl.coderslab.dto.UserDto;
import pl.coderslab.entity.Message;

public interface MessageService extends BaseCrudService<MessageDto, Long> {
	
	List<MessageDto> findAllByReceiver(UserDto reciver);
	List<MessageDto> findAllByReceiverId(Long id);
	
	List<MessageDto> findAllBySender(UserDto sender);
	List<MessageDto> findAllBySenderId(Long id);
}
