package jp.co.example.demo.login.domain.model;

import javax.validation.GroupSequence;

//バリデーションを実行する順番
@GroupSequence({ValidGroup1.class, ValidGroup2.class, ValidGroup3.class})
public interface GroupOrder {
	
	
}
