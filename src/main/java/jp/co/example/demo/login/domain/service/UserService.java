package jp.co.example.demo.login.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.demo.login.domain.model.User;
import jp.co.example.demo.login.domain.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	public boolean insert(User user) {
		//insert実行
		int rowNumber = dao.insertOne(user);
		//判定用変数
		boolean result = false;
		if(rowNumber > 0) {
			//insert成功
			result = true;
		}
		return result;
	}
	//カウント取得
	public int count() {
		return dao.count();
	}
	//全部取得
	public List<User> selectAll(){
		return dao.selectAll();
	}
	public User selectOne(String userId) {
		return dao.selectOne(userId);
	}
	
}
