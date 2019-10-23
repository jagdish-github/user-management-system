package com.jss.springbootdemo.entity;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class OperationStatus {
	private HttpMethod operationName;
	private OperationResult operationResult;
	public HttpMethod getOperationName() {
		return operationName;
	}
	public void setOperationName(HttpMethod opName) {
		this.operationName = opName;
	}
	public OperationResult getOperationResult() {
		return operationResult;
	}
	public void setOperationStatus(OperationResult operationResult) {
		this.operationResult = operationResult;
	}
	
	
	
}
