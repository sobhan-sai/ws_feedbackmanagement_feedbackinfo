package com.feedbackmanagement.feedbackinfo.entity;

import javax.persistence.Column;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Options {

	@Column(name="id")
	private String optionId;
	
	private String questionId;
	
	private String optionDescription;

	public String getOptionId() {
		return optionId;
	}

	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getOptionDescription() {
		return optionDescription;
	}

	public void setOptionDescription(String optionDescription) {
		this.optionDescription = optionDescription;
	}
	
	
}
