package jp.co.example.demo.login.domain.service;

import java.util.List;

import jp.co.example.demo.login.domain.model.User;

public interface RestService {
	
	public boolean insert(User user);
	
	public User selectOne(String userId);
	
	public List<User> selectAll();
	
	public boolean update(User user);
	
	public boolean delete(String userId);
	
	
}
