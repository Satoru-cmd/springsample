package jp.co.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect	//AOPのクラスにつける			  ）セットで覚える
@Component	//DIコンテナへBean定義をする  ）
public class LogAspect {
	//AOPの実装													
	//実行箇所の指定		//スペース
	@Before("execution(* jp.co.example.demo.login.controller.LoginController.getLogin(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("メソッド開始:" + jp.getSignature());
	}
	
	@After("execution(* jp.co.example.demo.login.controller.LoginController.getLogin(..))")
	public void endLog(JoinPoint jp) {
		System.out.println("メソッド終了:" + jp.getSignature());
	}
}
