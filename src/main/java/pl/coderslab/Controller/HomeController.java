package pl.coderslab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@SessionAttributes(names = "isLoggedIn")
public class HomeController {

  @GetMapping
  public ModelAndView indexPage(@ModelAttribute("isLoggedIn") Boolean isloggedIn) {
    if (isloggedIn) {
      return new ModelAndView("redirect:/user/homePage");
    }
    return new ModelAndView("index");
  }

  @ModelAttribute(name = "isLoggedIn")
  public Boolean isLoggedIn() {
    return false;
  }
}
