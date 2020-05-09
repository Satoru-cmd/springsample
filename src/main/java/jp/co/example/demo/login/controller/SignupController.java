package jp.co.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.example.demo.login.domain.model.SignupForm;

@Controller
public class SignupController {
	//ラジオボタンの実装
	private Map<String, String> radioMarriage;
	
	//ラジオボタンの初期化メソッド
	private Map<String, String> initRadioMarrige(){
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}
	//ユーザー登録画面のGET用コントローラー
	@GetMapping("/signup")			//キーはsignFormになる
	public String getSignup(@ModelAttribute SignupForm form, Model model) {
		//ラジオボタンの初期化メソッドの呼び出し
		radioMarriage = initRadioMarrige();
		
		//ラジオぼたん用のMapをModelに登録
		model.addAttribute("radioMarriage", radioMarriage);
		return "login/signup";
	}
	
	@PostMapping("/signup")		
	public String postSingup(@ModelAttribute SignupForm form, BindingResult bindingResult, Model model) {
		//入力チェックに引っかかった場合ユーザー登録画面に戻る
		if(bindingResult.hasErrors()) {
			return getSignup(form, model);
		}
		System.out.println(form.getAge());
		return "redirect:/login"; //LoginControllerのgetLoginメソッドが呼び出される
		
	}
	
}
