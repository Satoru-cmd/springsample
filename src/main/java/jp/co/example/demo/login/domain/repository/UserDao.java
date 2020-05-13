package jp.co.example.demo.login.domain.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;

import jp.co.example.demo.login.domain.model.User;

public interface UserDao {
	//Userテーブルの件数を取得
	public int count() throws DataAccessException;
	
	//Userテーブルのデータを一件挿入
	public int insertOne(User user) throws DataAccessException;
	
	//Userテーブルのデータを一件取得
	public User selectOne(String userId) throws DataAccessException;
	
	//Userテーブルの全データを取得
	public List<User> selectAll() throws DataAccessException;
	
	//Userてーぶるを一件更新
	public int updateOne(User user) throws DataAccessException;
	
	//Userテーブルを一件削除
	public int deleteOne(String userId) throws DataAccessException;
	
	//SQL取得結果をサーバーにCSVで保存
	public void userCsvOut() throws DataAccessException;
}
