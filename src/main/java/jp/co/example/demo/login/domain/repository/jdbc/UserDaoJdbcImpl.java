package jp.co.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.demo.login.domain.model.User;
import jp.co.example.demo.login.domain.repository.UserDao;

@Repository
public class UserDaoJdbcImpl implements UserDao{

	@Autowired
	JdbcTemplate jdbc;
	
	//Userテーブルの件数を取得
	@Override
	public int count() throws DataAccessException{
		return 0;
	}
	
	//Userテーブルのデータを１件挿入
	@Override
	public int insertOne(User user) throws DataAccessException{
		return 0;
	}
	//Userテーブルを一件取得
	@Override
	public User selectOne(String userId) throws DataAccessException{
		return null;
	}
	//Userテーブルを全部取得
	@Override
	public List<User> selectAll() throws DataAccessException{
		return null;
	}
	//Userテーブルを一件更新
	@Override
	public int updateOne(User user) throws DataAccessException{
		return 0;
	}
	//Userテーブルを一件削除
	@Override 
	public int deleteOne(String userId) throws DataAccessException{
		return 0;
	}
	
	//Userテーブルの全データをCSVにする
	@Override
	public void userCsvOut() throws DataAccessException{
	}
	
	
}
