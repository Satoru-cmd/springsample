package jp.co.example.demo.trySpring;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelloService {
	
	@Autowired
	private HelloRepository hellorepo;
	
	public Employee findone(int id) {
		Map<String, Object> map = hellorepo.findone(id);
		
		int employeeId = (Integer)map.get("employee_id");
		String employeeName = (String)map.get("employee_name");
		int age = (Integer)map.get("age");
		
		Employee employee = new Employee();
		employee.setEmployeeId(employeeId);
		employee.setEmployeeName(employeeName);
		employee.setAge(age);
		
		return employee;
		}
}
