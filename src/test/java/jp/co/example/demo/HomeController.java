package jp.co.example.demo;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import jp.co.example.demo.login.domain.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeController {

	@Autowired
	private MockMvc mockMvc;
	
	//Mockの戻り値設定
	@MockBean
	private UserService service;
	
	@Test
	@WithMockUser //ログインした後にしか表示できない画面のテスト
	public void ユーザーリスト画面のユーザー件数のテスト() throws Exception{
		//Mockmの戻り値設定
		//UserServiceのcountメソッドの戻り値を１０に設定
		when(service.count()).thenReturn(10);
		
		//ユーザー画面のチェック
		mockMvc.perform(get("/userList")).andExpect(status().isOk())
		.andExpect(content().string(containsString("合計:10件")));
	}
	
	@Test
	@WithMockUser(username="satou", roles= "{ROLE_ADMIN}")
	public void ユーザー情報() throws Exception{
		Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
		String message = auth.getName() + "\n" + auth.getCredentials() + "\n" + auth.getAuthorities() + 
				auth.getClass() + "\n" + auth.getDetails() + "\n" + auth.getPrincipal();
		System.out.println(message);
		
//		Authentication user = SecurityContextHolder.getContext().getAuthentication();
//		String name = user.getName();
//		System.out.println(name);
		
	}
	

}
