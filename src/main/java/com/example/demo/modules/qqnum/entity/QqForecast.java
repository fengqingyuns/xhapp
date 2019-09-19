package com.example.demo.modules.qqnum.entity;

public class QqForecast {

	private String qq_id;

	private int errorCode;
	
	private String reason;
	
	private String result;
	
	private String conclusion;
	
	private String analysis;

	

	

	public String getQq_id() {
		return qq_id;
	}

	public void setQq_id(String qq_id) {
		this.qq_id = qq_id;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getConclusion() {
		return conclusion;
	}

	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}

	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}

	@Override
	public String toString() {
		return "QqForecast [qq_id=" + qq_id + ", errorCode=" + errorCode + ", reason=" + reason + ", result=" + result
				+ ", conclusion=" + conclusion + ", analysis=" + analysis + "]";
	}

	

	
	
}
