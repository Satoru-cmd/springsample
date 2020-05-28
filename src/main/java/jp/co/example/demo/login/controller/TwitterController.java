package jp.co.example.demo.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.example.demo.login.domain.model.TwitterForm;
import jp.co.example.demo.login.domain.model.User;
import jp.co.example.demo.login.domain.service.UserService;

@Controller
public class TwitterController {

	@Autowired
	UserService userService;

	@GetMapping("/twitter")
	public String twitter(Model model) {
		model.addAttribute("contents", "login/twitter :: twitter_contents");
		return "login/homeLayout";
	}

	@PostMapping("/contribute")
	public String postTwitter(Model model) {
		model.addAttribute("contents", "login/twitter :: twitter_contents");
		return "login/homeLayout";
	}
}