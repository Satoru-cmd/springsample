package jp.co.example.demo;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class RestMatcher implements RequestMatcher{
	private AntPathRequestMatcher matcher;
	
	public RestMatcher(String url) {
		super();
		matcher = new AntPathRequestMatcher(url);
	}
	
	//URlのマッチ条件
	@Override
	public boolean matches(HttpServletRequest request) {
		if("GET".equals(request.getMethod())) {
			return false;
		}
	
	//特定のURLに該当する場合、CSRFチェックしない
	if(matcher.matches(request)) {
		return false;
	}
	return true;
	}
}
