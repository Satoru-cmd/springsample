package jp.co.example.demo.login.domain.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.demo.login.domain.model.User;

@Repository("UserDaoJdbcImpl2") //Bean名セット
public class UserDaoJdbcImpl2 extends UserDaoJdbcImpl{

	@Autowired
	private JdbcTemplate jdbc;
	
	@Override
	public User selectOne(String userId) {
		String sql = "SELECT * FROM m_user WHERE user_id=?";
		//RowMapperの生成
		UserRowMapper rowMapper = new UserRowMapper();
		//SQL実行
		return jdbc.queryForObject(sql, rowMapper, userId);
	}
	
	@Override
	public List<User> selectAll(){
		String sql = "SELECT * FROM m_user";
		UserRowMapper rowMapper = new UserRowMapper();
		return jdbc.query(sql, rowMapper);
	}
	
}
