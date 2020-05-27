package jp.co.example.demo.login.domain.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TwitterForm {
	
	@NotBlank
	private String writing;

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}
	
	
}
