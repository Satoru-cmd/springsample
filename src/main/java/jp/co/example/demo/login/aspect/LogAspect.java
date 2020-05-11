package jp.co.example.demo.login.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

//@Aspect	//AOPのクラスにつける			  ）セットで覚える
//@Component	//DIコンテナへBean定義をする  ）
////public class LogAspect {
	//AOPの実装													
//	//実行箇所の指定		//スペース
//	@Around("execution(* *..*.*Controller.*(..))")
//	public void Object startLog(ProceedingJoinPoint jp) throws Throwable{
//		System.out.println("メソッド開始:" + jp.getSignature());
//		try {
//			Object result = jp.pro
//		}
//	}
//	
//	@After("execution(* *..*.*Controller.*(..))")
//	public void endLog(JoinPoint jp) {
//		System.out.println("メソッド終了:" + jp.getSignature());
//	}
//}
