package com.blackmoon.questions;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;

public class QuestionObject {
	private String txtQuestion;
	private String txtAnswer1E;
	private String txtAnswer1V;

	private String txtAnswer2E;
	private String txtAnswer2V;

	private String txtAnswer3E;
	private String txtAnswer3V;

	private String txtAnswer4E;
	private String txtAnswer4V;

	private String rightAnswer;

	public QuestionObject() {
		setTxtQuestion("Cô ấy rất xinh đẹp và tinh tế");
		setTxtAnswer1E("She so beautiful");
		setTxtAnswer1V("Cô ấy rất xinh đẹp");
		
		setTxtAnswer2E("She so lovely");
		setTxtAnswer2V("Cô ấy dễ thương");
		
		setTxtAnswer3E("She so wonderfull");
		setTxtAnswer3V("Cô ấy rất tuyệt vời");
		
		setTxtAnswer4E("She so beautiful and precise");
		setTxtAnswer4V("Cô ấy rất xinh đẹp và tinh tế");
		setRightAnswer("D");
		
	}

	public String getTxtQuestion() {
		return txtQuestion;
	}

	public void setTxtQuestion(String txtQuestion) {
		this.txtQuestion = txtQuestion;
	}

	public String getTxtAnswer1E() {
		return txtAnswer1E;
	}

	public void setTxtAnswer1E(String txtAnswer1E) {
		this.txtAnswer1E = txtAnswer1E;
	}

	public String getTxtAnswer1V() {
		return txtAnswer1V;
	}

	public void setTxtAnswer1V(String txtAnswer1V) {
		this.txtAnswer1V = txtAnswer1V;
	}

	public String getTxtAnswer2E() {
		return txtAnswer2E;
	}

	public void setTxtAnswer2E(String txtAnswer2E) {
		this.txtAnswer2E = txtAnswer2E;
	}

	public String getTxtAnswer2V() {
		return txtAnswer2V;
	}

	public void setTxtAnswer2V(String txtAnswer2V) {
		this.txtAnswer2V = txtAnswer2V;
	}

	public String getTxtAnswer3V() {
		return txtAnswer3V;
	}

	public void setTxtAnswer3V(String txtAnswer3V) {
		this.txtAnswer3V = txtAnswer3V;
	}

	public String getTxtAnswer3E() {
		return txtAnswer3E;
	}

	public void setTxtAnswer3E(String txtAnswer3E) {
		this.txtAnswer3E = txtAnswer3E;
	}

	public String getTxtAnswer4E() {
		return txtAnswer4E;
	}

	public void setTxtAnswer4E(String txtAnswer4E) {
		this.txtAnswer4E = txtAnswer4E;
	}

	public String getTxtAnswer4V() {
		return txtAnswer4V;
	}

	public void setTxtAnswer4V(String txtAnswer4V) {
		this.txtAnswer4V = txtAnswer4V;
	}

	public String getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(String rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

}
