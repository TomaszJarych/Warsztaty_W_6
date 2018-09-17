package pl.coderslab.Controller;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.coderslab.Service.TweetService;
import pl.coderslab.Service.UserService;
import pl.coderslab.dto.TweetDto;
import pl.coderslab.dto.UserDto;

@Controller
@RequestMapping("/user")
@SessionAttributes(names = {"userDto", "isLoggedIn"})
public class UserController {

  private final UserService userService;
  private final TweetService tweetService;

  @Autowired
  public UserController(UserService userService, TweetService tweetService) {
    this.userService = userService;
    this.tweetService = tweetService;
  }

  @GetMapping("/register")
  public ModelAndView registerNewUser(Model model) {

    return new ModelAndView("user/register");
  }

  @PostMapping("/register")
  public ModelAndView processRegisterNewUser(
      @Valid @ModelAttribute("userDto") UserDto dto,
      BindingResult result,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model) {
    if (result.hasErrors()) {
      return new ModelAndView("user/register");
    }
    dto = userService.saveToDB(dto);
    model.addAttribute("isLoggedIn", true);
    model.addAttribute("userDto", dto);
    return new ModelAndView("redirect:/user/index");
  }

  @GetMapping("/login")
  public ModelAndView loginUser(Model model) {

    return new ModelAndView("user/login");
  }

  @PostMapping("/login")
  public ModelAndView loginUser(
      @ModelAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model) {
    UserDto user = userService.login(dto);
    if (Objects.nonNull(user)) {
      isLoggedIn = true;
      dto = user;
      model.addAttribute("isLoggedIn", true);
      model.addAttribute("userDto", user);
      return new ModelAndView("redirect:/user/index");
    }
    return new ModelAndView("user/login");
  }

  @GetMapping("/index")
  public ModelAndView showHomePage(
      @ModelAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model) {
    model.addAttribute("tweets", tweetService.findAllByUser(dto));

    return new ModelAndView("user/userHomePage");
  }

  @GetMapping("/logout")
  public ModelAndView logoutUser(Model model) {

    model.addAttribute("isLoggedIn", false);
    model.addAttribute("userDto", new UserDto());

    return new ModelAndView("redirect:/");
  }

  @ModelAttribute("userDto")
  private UserDto getUserDto() {
    return new UserDto();
  }
  
  @ModelAttribute("tweet")
  private TweetDto getTweet() {
	  return new TweetDto();
  }
}
