package com.feedbackmanagement.feedbackinfo.entity;

import javax.persistence.Column;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Question {
	@Column(name="_id")
	private String questionId;
	private String questionDescription;
	
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionDescription() {
		return questionDescription;
	}
	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}
	
	
}
