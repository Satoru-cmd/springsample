package jp.co.example.demo.login.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jp.co.example.demo.login.domain.model.SignupForm;
import jp.co.example.demo.login.domain.model.User;
import jp.co.example.demo.login.domain.model.UserSession;
import jp.co.example.demo.login.domain.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserSession userSession;
	
//	@Autowired
//	User user;
	
	private Map<String, String> radioMarriage;
	
	private Map<String, String> initRadioMarriage(){
		Map<String, String> radio = new LinkedHashMap<>();
		radio.put("既婚", "true");
		radio.put("未婚", "false");
		return radio;
	}
	@GetMapping("/home")
	public String getHome(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		User user = userService.selectOne(userId);
		String userName = user.getUserName();
		model.addAttribute("userName",userName);
		
		//conntents部分にホーム画面を表示すために文字列を登録
		model.addAttribute("contents", "login/home ::home_contents");
		return"login/homeLayout";
	}
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userId = auth.getName();
		User user = userService.selectOne(userId);
		String userName = user.getUserName();
		model.addAttribute("userName",userName);
		
		model.addAttribute("contents", "login/userList::userList_contents");
		List<User> userList = userService.selectAll();
		model.addAttribute("userList", userList);
		
		int count = userService.count();
		model.addAttribute("userListCount", count);
		return "login/homeLayout";
		}
	@PostMapping("/logout")
	public String postHome() {
		//ログイン画面にリダイレクトする
		return "redirect:/login";
	}
	
	
							//動的URL
	@GetMapping("/userDetail/{id:.+}")	 //PathVariable->URLの値を引数に入れる
	public String getUserDetail(@ModelAttribute SignupForm form, Model model,
			@PathVariable("id")String userId){
		System.out.println("userId=" + userId);
		model.addAttribute("contents", "login/userDetail::userDetail_contents");
		radioMarriage = initRadioMarriage();
		model.addAttribute("radioMarriage", radioMarriage);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userid = auth.getName();
		User users = userService.selectOne(userid);
		String userName = users.getUserName();
		model.addAttribute("userName",userName);
		
		if(userId != null && userId.length() > 0) {
			User user = userService.selectOne(userId);
			form.setUserId(user.getUserId());
			form.setPassword(user.getPassword());
			form.setUserName(user.getUserName());
			form.setBirthday(user.getBirthday());
			form.setAge(user.getAge());
			form.setMarriage(user.isMarriage());
			System.out.println(user.getPassword());
			model.addAttribute("signupForm", form);
		}
		return "login/homeLayout";
		
	}
									//URLとbuttonのname属性を見る
	@PostMapping(value="/userDetail", params="update")
	public String postUserDetailUpdate(@ModelAttribute SignupForm form, Model model) {
		User user = new User();
		user.setUserId(form.getUserId());
		user.setPassword(form.getPassword());
		user.setBirthday(form.getBirthday());
		user.setMarriage(form.isMarriage());
		user.setUserName(form.getUserName());
		user.setAge(form.getAge());
		
		boolean result = userService.updateOne(user);
		if(result = true) {
			model.addAttribute("result", "更新成功");
		}else {
			model.addAttribute("result", "更新失敗");
		}
		return getUserList(model);
	}
	
	@PostMapping(value="/userDetail", params="delete")
	public String postUserDelete(@ModelAttribute SignupForm form, Model model) {
		System.out.println("削除ボタンの処理");
		boolean result = userService.delete(form.getUserId());
		if(result = true) {
			model.addAttribute("result", "削除成功");
		}else {
			model.addAttribute("result", "削除失敗");
		}
		return getUserList(model);
	}
	
	@GetMapping("/userList/csv")
	public ResponseEntity<byte[]> getUserListCsv(Model model){
		//ユーザーを全権取得してCSVをサーバーに保存
		userService.UserCsvOut();
		byte[] bytes = null;
		try {
			//サーバーに保存されているsample.csvファイルをbyteで取得
			bytes = userService.getFile("sample.csv");
		}catch(IOException e) {
			e.printStackTrace();
		}
		//HTTPヘッダーの設定
		HttpHeaders header = new HttpHeaders();
		header.add("Content-Type", "text/csv;charset=UTF-8");
		header.setContentDispositionFormData("filename", "sample.csv");
		
		//sample.csvを戻す
		return new ResponseEntity<>(bytes, header, HttpStatus.OK);
				 //ResponseEntity<> -> htmlではなくファイル(byte型の配列）を戻す
	}
	
	@GetMapping("/admin")
	public String getAdmin(Model model) {
		model.addAttribute("contents", "login/admin :: admin_contents");
		return "login/homeLayout";
	}
	
	
	
}
