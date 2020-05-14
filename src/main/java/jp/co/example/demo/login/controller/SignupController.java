package jp.co.example.demo.login.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.example.demo.login.domain.model.GroupOrder;
import jp.co.example.demo.login.domain.model.SignupForm;
import jp.co.example.demo.login.domain.model.User;
import jp.co.example.demo.login.domain.service.UserService;

@Controller
public class SignupController {
	@Autowired
	private UserService userService;
	
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
	
	@PostMapping("/signup")					//@Validated->バリデーションの実施					//BindingResult->バインドの結果
	public String postSingup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form, BindingResult bindingResult, Model model) {
		//入力チェックに引っかかった場合ユーザー登録画面に戻る
		if(bindingResult.hasErrors()) {
			return getSignup(form, model);
		}
		System.out.println(form.getAge());
		//insert用変数
		User user = new User();
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setUserName(form.getUserName());
		user.setBirthday(form.getBirthday());
		user.setAge(form.getAge());
		user.setMarriage(form.isMarriage());
		user.setRole("ROLE_GENARAL");
		
		//ユーザー登録処理
		boolean result = userService.insert(user);
		if(result == true) {
			System.out.println("insert成功");
		}else {
			System.out.println("insert失敗");
		}
		
		return "redirect:/login"; //LoginControllerのgetLoginメソッドが呼び出される
		
	}
	
}
