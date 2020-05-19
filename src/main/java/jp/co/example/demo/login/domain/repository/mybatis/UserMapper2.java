package jp.co.example.demo.login.domain.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.co.example.demo.login.domain.model.User;

@Mapper
public interface UserMapper2 {
	
	public boolean insert(User user);
	
	public User selectOne(String userId);
	
	public List<User> selectAll();
	
	public boolean updateOne(User user);
	
	public boolean deleteOne(String userId);
}
