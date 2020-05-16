package jp.co.example.demo.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import jp.co.example.demo.login.domain.model.User;

//複数件のSELECT結果をマッピングするのに使う
public class UserResultSetExtractor implements ResultSetExtractor<List<User>>{
	
	@Override
	public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException{
		//User用格納List
		List<User> userList = new ArrayList<>();
		//取得件数文のループ
		while(rs.next()) {
			User user = new User();
			//Userインスタンスにセット
			user.setUserId(rs.getString("user_id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setAge(rs.getInt("age"));
			user.setBirthday(rs.getDate("birthday"));
			user.setMarriage(rs.getBoolean("marriage"));
			user.setRole(rs.getString("role"));
			userList.add(user);
		}
		if(userList.size() == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		return userList;
	}
}
