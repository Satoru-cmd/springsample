package jp.co.example.demo.login.domain.model;

import java.util.Date;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {
	@NotBlank(groups = ValidGroup1.class)	//入力必須
	@Email(groups = ValidGroup1.class)
	private String userId;
	
	@NotBlank(message="パスワードは必須です", groups = ValidGroup1.class)
	@Length(min=4, max=100, groups = ValidGroup1.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup1.class)
	private String password;
	
	@NotBlank(message="ユーザー名は必須入力です", groups = ValidGroup1.class)
	private String userName;
	
	@NotNull(groups = ValidGroup1.class)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date birthday;
	
	@Min(value = 20, groups = ValidGroup1.class)
	@Max(value = 100, groups = ValidGroup1.class)
	private int age;
	
	@AssertFalse(groups = ValidGroup2.class)	//falseのみ可能
	private boolean marriage;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public boolean isMarriage() {
		return marriage;
	}
	public void setMarriage(boolean marriage) {
		this.marriage = marriage;
	}
	
	
}
