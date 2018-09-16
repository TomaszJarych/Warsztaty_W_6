package pl.coderslab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import pl.coderslab.dto.UserDto;

@Controller
@RequestMapping("/")
@SessionAttributes(names = {"isLoggedIn", "userDto"})
public class HomeController {

  @GetMapping
  public ModelAndView indexPage(@ModelAttribute("isLoggedIn") Boolean isloggedIn, Model model) {
    if (isloggedIn) {
      return new ModelAndView("redirect:/user/index");
    }
    return new ModelAndView("welcomePage");
  }

  @ModelAttribute(name = "isLoggedIn")
  public Boolean isLoggedIn() {
    return false;
  }

  @ModelAttribute("userDto")
  public UserDto getUserDto() {
    return new UserDto();
  }
}
