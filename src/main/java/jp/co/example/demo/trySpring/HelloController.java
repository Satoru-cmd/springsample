package jp.co.example.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

	@Autowired
	private HelloService helloservice;
	
	@GetMapping("/hello")
	public String getHello() {
		return "hello";
	}
	
	@PostMapping("/hello")
	public String postRequest(@RequestParam("text1")String str,
			Model model) {
		model.addAttribute("sample", str);
		return "helloResponse";
	}
	
	@PostMapping("/hello/DB")
	public String postDBrequest(@RequestParam("text2")String str,
			Model model) {
		int id = Integer.parseInt(str);
		Employee employee = helloservice.findone(id);
		int employeeid = employee.getEmployeeId();
		String employeename = employee.getEmployeeName();
		int age = employee.getAge();
		
		model.addAttribute("id", employeeid);
		model.addAttribute("name", employeename);
		model.addAttribute("age", age);
		
		return "helloDB";
	}
}
