package jp.co.example.demo.login.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {
	
//	@Autowired
//	UserService userService;
	
	@GetMapping("/home")
	public String getHome(Model model) {
		//conntents部分にホーム画面を表示するために文字列を登録
		model.addAttribute("contents", "login/home ::home_contents");
		return"login/homeLayout";
	}
	
	@PostMapping("/logout")
	public String postHome() {
		//ログイン画面にリダイレクト
		return "redirect:/login";
	}
	
	
}
