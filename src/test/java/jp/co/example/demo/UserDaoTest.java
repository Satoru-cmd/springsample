package jp.co.example.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jp.co.example.demo.login.domain.repository.UserDao;

@RunWith(SpringRunner.class) //テストをどのクラスで実行するか指定 SpringRunnerはSpring用のJunit
@SpringBootTest	//SpringBootを起動してからテストをはじめてくれる
@Transactional	//トランザクションを使う
public class UserDaoTest {

//リポジトリークラスのテスト
	@Autowired
	@Qualifier("UserDaoJdbcImpl")
	UserDao dao;
	
	@Test
	public void countTest1() {
		
		assertEquals(dao.count(), １);
	}
	
	@Test
	@Sql("/testdate.sql")
	public void countTest2() {
		assertEquals(dao.count(), 2);
	}
}
