package com.feedbackmanagement.feedbackinfo.entity;

import lombok.Data;

@Data
public class Message {
	
	private String Content;
	public Message() {
	}

	public Message(String content) {
		Content = content;
	}
}
