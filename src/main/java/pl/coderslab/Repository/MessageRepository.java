package pl.coderslab.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.coderslab.entity.Message;
import pl.coderslab.entity.User;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findAllByReceiver(User reciver);
	List<Message> findAllByReceiverId(Long id);
	
	List<Message> findAllBySender(User sender);
	List<Message> findAllBySenderId(Long id);
	
	
}
