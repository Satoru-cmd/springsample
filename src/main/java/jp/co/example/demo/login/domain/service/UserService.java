package jp.co.example.demo.login.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.example.demo.login.domain.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	UserDao dao;
	
	
}
