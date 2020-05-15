package jp.co.example.demo.login.domain.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import jp.co.example.demo.login.domain.model.User;

//RowMapper<マッピングに使うJavaオブジェクト>
public class UserRowMapper implements RowMapper<User>{

	@Override		  //Select結果が入る
	public User mapRow(ResultSet rs, int rowNum) throws SQLException{
		//戻り値用のUserインスタンスを生成
		User user = new User();
		
		//ResultSetの取得結果をUserインスタンスにセット
		user.setUserId(rs.getString("user_id"));
		user.setUserName(rs.getNString("user_name"));
		user.setPassword(rs.getNString("password"));
		user.setBirthday(rs.getDate("birthday"));
		user.setAge(rs.getInt("age"));
		user.setMarriage(rs.getBoolean("marriage"));
		user.setRole(rs.getString("role"));
		return user;
	}
}
