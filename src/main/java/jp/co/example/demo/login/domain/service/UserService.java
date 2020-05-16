package jp.co.example.demo.login.domain.service;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import jp.co.example.demo.login.domain.model.User;
import jp.co.example.demo.login.domain.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	@Qualifier("UserDaoNamedJdbcImpl") //どのBeanを使うか指定
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
	
	//一件更新
	public boolean updateOne(User user) {
		int rowNumber = dao.updateOne(user);
		boolean result = false;
		if( rowNumber > 0) {
			result = true;
		}
		return result;
	}
	
	//一件削除
	public boolean delete(String userId) {
		int rowNumber = dao.deleteOne(userId);
		boolean result = false;
		if(rowNumber > 0) {
			result = true;
		}
		return result;
	}
	
	//ユーザー一覧をCSV出力
	public void UserCsvOut() throws DataAccessException{
		dao.userCsvOut();
	}
	
	//サーバーに保存されているファイルを取得して、byte配列に変換する
	public byte[] getFile(String fileName) throws IOException{
		//ファイルシステムの取得
		FileSystem fs = FileSystems.getDefault();
		//ファイル取得
		Path p = fs.getPath(fileName);
		//ファイルをbyte配列に変換
		byte[] bytes = Files.readAllBytes(p);
		return bytes;
	}
}
