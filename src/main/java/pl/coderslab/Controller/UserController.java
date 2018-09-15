package pl.coderslab.Controller;

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

import pl.coderslab.Service.UserService;
import pl.coderslab.dto.UserDto;

@Controller
@RequestMapping("/user")
@SessionAttributes(names = {"userDto", "isLoggedIn"})
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/register")
  public ModelAndView registerNewUser() {

    return new ModelAndView("user/register");
  }

  @PostMapping("/register")
  public ModelAndView processRegisterNewUser(
      @Valid @ModelAttribute("userDto") UserDto dto,
      BindingResult result,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn) {
    if (result.hasErrors()) {
      return new ModelAndView("user/register");
    }
    
    dto = userService.saveToDB(dto);
    isLoggedIn = true;
    return new ModelAndView("user/userHomePage");
  }

  @ModelAttribute("userDto")
  private UserDto getUserDto() {
    return new UserDto();
  }
}
