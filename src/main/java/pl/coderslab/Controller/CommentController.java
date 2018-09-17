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

import pl.coderslab.Service.CommentService;
import pl.coderslab.Service.TweetService;
import pl.coderslab.Service.UserService;
import pl.coderslab.dto.CommentDto;
import pl.coderslab.dto.UserDto;

@Controller
@RequestMapping("/comment")
@SessionAttributes(names = {"isLoggedIn", "userDto"})
public class CommentController {

  private final TweetService tweetService;
  private final UserService userService;
  private final CommentService commentService;

  @Autowired
  public CommentController(
      TweetService tweetService, UserService userService, CommentService commentService) {
    this.tweetService = tweetService;
    this.userService = userService;
    this.commentService = commentService;
  }

  @GetMapping("/add")
  public ModelAndView addNewComment(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model) {
    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }

    model.addAttribute("comment", new CommentDto());

    return new ModelAndView("comment/commentForm");
  }

  @PostMapping("/add")
  public ModelAndView processNewComment(
      @Valid @ModelAttribute("comment") CommentDto dto, BindingResult result) {
	  if (result.hasErrors()) {
		  return new ModelAndView("comment/commentForm");
  }
	  commentService.saveToDB(dto);
    return new ModelAndView("redirect:/");
  }
}
