package pl.coderslab.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
@SessionAttributes("isLoggedIn")
public class HomeController {

	@GetMapping
	public ModelAndView getIndexPage(
			@ModelAttribute("isLoggedIn") Boolean isloggedIn) {
		if (isloggedIn) {
			return new ModelAndView("index");
		}
		return new ModelAndView("redirect:/login");
	}

	@ModelAttribute("isLoggedIn")
	public boolean isLogged() {

		return false;
	}
}
