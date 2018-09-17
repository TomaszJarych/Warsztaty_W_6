package pl.coderslab.Controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.coderslab.Service.MessageService;
import pl.coderslab.Service.UserService;
import pl.coderslab.dto.MessageDto;
import pl.coderslab.dto.UserDto;

@Controller
@RequestMapping("/message")
@SessionAttributes(names = {"isLoggedIn", "userDto"})
public class MessageController {

  private final MessageService messageService;
  private final UserService userService;

  @Autowired
  public MessageController(MessageService messageService, UserService userService) {
    this.messageService = messageService;
    this.userService = userService;
  }

  @GetMapping("/showMyMessages")
  public ModelAndView showAllMessages(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model) {

    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }

    model.addAttribute("messages", messageService.findAllByReceiver(dto));
    model.addAttribute("sendedMessages", messageService.findAllBySender(dto));

    return new ModelAndView("message/messages");
  }

  @GetMapping("/add")
  public ModelAndView showMessageForm(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model) {
    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }
    model.addAttribute("message", new MessageDto());

    return new ModelAndView("message/messageForm");
  }

  @PostMapping("/add")
  public ModelAndView processMessageForm(
      @Valid @ModelAttribute("message") MessageDto dto, BindingResult result) {
    if (result.hasErrors()) {
      return new ModelAndView("message/messageForm");
    }
    messageService.saveToDB(dto);
    return new ModelAndView("redirect:/message/showMyMessages");
  }

  @GetMapping("/detail/{id}")
  public ModelAndView showMessageDetail(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model,
      @PathVariable("id") Long id) {
    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }
    MessageDto message = messageService.findById(id);
    if (message.getReceiver().getId() == dto.getId()) {
      message.setIsNewMessage(false);
      message = messageService.saveToDB(message);
    }
    model.addAttribute("message", message);
    return new ModelAndView("message/messageDetail");
  }

  @GetMapping("/delete/{id}")
  public ModelAndView deleteMessage(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model,
      @PathVariable("id") Long id) {
    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }
    MessageDto message = messageService.findById(id);
    if (message.getReceiver().getId() == dto.getId()) {
      messageService.removeFromDB(id);
    }
    return new ModelAndView("redirect:/message/showMyMessages");
  }

  @ModelAttribute("receivers")
  private List<UserDto> receivers(@SessionAttribute("userDto") UserDto dto) {
    return userService
        .findAll()
        .stream()
        .filter(el -> el.getId() != dto.getId())
        .collect(Collectors.toList());
  }
}
