package jp.co.example.demo.login.domain.model;

import java.io.Serializable;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

//使うかわからない

@Component
@Scope(value="session", proxyMode=ScopedProxyMode.TARGET_CLASS)
public class TwitterSession implements Serializable{

	private static final long serialVersionUID =  6334063099671792256L;
	
	
}
