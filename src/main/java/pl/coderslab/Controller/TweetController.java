package pl.coderslab.Controller;

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

import pl.coderslab.Repository.TweetRepository;
import pl.coderslab.Service.CommentService;
import pl.coderslab.Service.TweetService;
import pl.coderslab.Service.UserService;
import pl.coderslab.dto.CommentDto;
import pl.coderslab.dto.TweetDto;
import pl.coderslab.dto.UserDto;

@Controller
@RequestMapping("/tweet")
@SessionAttributes(names = {"isLoggedIn", "userDto"})
public class TweetController {

  private final TweetService tweetService;
  private final UserService userService;
  private final CommentService commentService;

  @Autowired
  public TweetController(TweetService tweetService, UserService userService,CommentService commentService) {
    this.tweetService = tweetService;
    this.userService = userService;
    this.commentService = commentService;
  }

  @GetMapping("/add")
  public ModelAndView showTweetForm(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model) {
    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }

    model.addAttribute("tweet", new TweetDto());

    return new ModelAndView("tweet/tweetForm");
  }

  @PostMapping("/add")
  public ModelAndView processNewTweetForm(
      @Valid @ModelAttribute("tweet") TweetDto dto, BindingResult result) {
    if (result.hasErrors()) {
      return new ModelAndView("tweet/tweetForm");
    }
    tweetService.saveToDB(dto);
    return new ModelAndView("redirect:/");
  }

  @GetMapping("/update/{id}")
  public ModelAndView showEditTweetForm(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      Model model,
      @PathVariable("id") Long id) {
    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }

    model.addAttribute("tweet", tweetService.findById(id));

    return new ModelAndView("tweet/tweetForm");
  }

  @PostMapping("/update/**")
  public ModelAndView processEditTweetForm(
      @Valid @ModelAttribute("tweet") TweetDto dto, BindingResult result) {
    if (result.hasErrors()) {
      return new ModelAndView("tweet/tweetForm");
    }
    tweetService.update(dto);
    return new ModelAndView("redirect:/");
  }

  @GetMapping("/delete/{id}")
  public ModelAndView deleteTweet(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      @PathVariable("id") Long id) {
    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }

    tweetService.removeFromDB(id);
    return new ModelAndView("redirect:/");
  }

  @GetMapping("/detail/{id}")
  public ModelAndView showTweetDetail(
      @SessionAttribute("userDto") UserDto dto,
      @SessionAttribute("isLoggedIn") Boolean isLoggedIn,
      @PathVariable("id") Long id, Model model) {
    if (isLoggedIn == null || isLoggedIn == false || dto.getId() == null) {
      return new ModelAndView("redirect:/");
    }
    
    model.addAttribute("tweetDetail", tweetService.findById(id));
    model.addAttribute("comments", commentService.findAllByTweetIdOrderByCreatedDesc(id));
    model.addAttribute("comment", new CommentDto());
    model.addAttribute("count", commentService.countByTweetId(id));

    return new ModelAndView("tweet/tweetDetail");
  }
}
