package jp.co.example.demo.login.domain.repository.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.example.demo.login.domain.model.User;
import jp.co.example.demo.login.domain.repository.UserDao;

@Repository("UserDaoJdbcImpl") //Bean名セット
public class UserDaoJdbcImpl implements UserDao{

	@Autowired
	JdbcTemplate jdbc;
	
	//Userテーブルの件数を取得
	@Override
	public int count() throws DataAccessException{
		//Countやカラムが一つだけの時は quiryForObject
		int count = jdbc.queryForObject("SELECT COUNT(*) FROM m_user", Integer.class);
		return count;
	}
	
	//Userテーブルのデータを１件挿入
	@Override
	public int insertOne(User user) throws DataAccessException{
		int rowNumber = jdbc.update("INSERT INTO m_user(user_id,"
				+"password, user_name, birthday, age, marriage, role)VALUES(?,?,?,?,?,?,?)"
				,user.getUserId(), user.getPassword(),  user.getUserName(), user.getBirthday()
				,user.getAge(), user.isMarriage(), user.getRole());
		return rowNumber;
	}
	//Userテーブルを一件取得
	@Override
	public User selectOne(String userId) throws DataAccessException{
		Map<String, Object> map =jdbc.queryForMap("SELECT * FROM m_user WHERE user_id = ?", userId);
		User user = new User();
		user.setUserId((String)map.get("user_id"));
		user.setUserName((String)map.get("user_name"));
		user.setPassword((String)map.get("password"));
		user.setBirthday((Date)map.get("birthday"));
		user.setAge((Integer)map.get("age"));
		user.setMarriage((Boolean)map.get("marriage"));
		user.setRole((String)map.get("role"));
		return user;
	}
	//Userテーブルを全部取得
	@Override
	public List<User> selectAll() throws DataAccessException{
		List<Map<String, Object>> getList = jdbc.queryForList("SELECT * FROM m_user");
		//結果返却用変数
		List<User> userList = new ArrayList<>();
		for(Map<String, Object> map : getList) {
			User user = new User();
			//Userインスタンスに取得したデータをセット
			user.setUserId((String)map.get("user_id"));
			user.setPassword((String)map.get("password"));
			user.setUserName((String)map.get("user_name"));
			user.setBirthday((Date)map.get("birthday"));
			user.setAge((Integer)map.get("age"));
			user.setMarriage((Boolean)map.get("marriage"));
			user.setRole((String)map.get("role"));
			userList.add(user);
		}
		return userList;
	}
	
	//Userテーブルを一件更新
	@Override
	public int updateOne(User user) throws DataAccessException{
		int rowNumber = jdbc.update("UPDATE m_user SET password=?,user_name=?,birthday=?,age=?,marriage=? WHERE user_id=?"
				,user.getPassword(), user.getUserName(), user.getBirthday(), user.getAge(), user.isMarriage(), user.getUserId());
		return rowNumber;
	}
	
	//Userテーブルを一件削除
	@Override 
	public int deleteOne(String userId) throws DataAccessException{
		int rowNumber = jdbc.update("DELETE FROM m_user WHERE user_id=?",userId );
		return rowNumber;
	}
	
	//Userテーブルの全データをCSVにする
	@Override
	public void userCsvOut() throws DataAccessException{
		//m_userてーぶるのデータを全権取得
		String sql = "SELECT * FROM m_user";
		
		UserRowCallbackHandler handler = new UserRowCallbackHandler();
		//SQL実行＆CSV出力
		jdbc.query(sql, handler);
	}
	
	
}
