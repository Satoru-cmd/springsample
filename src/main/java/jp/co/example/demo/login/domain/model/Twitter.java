package jp.co.example.demo.login.domain.model;

import lombok.Data;

@Data
public class Twitter {

	private String writing;

	public String getWriting() {
		return writing;
	}

	public void setWriting(String writing) {
		this.writing = writing;
	}
}
