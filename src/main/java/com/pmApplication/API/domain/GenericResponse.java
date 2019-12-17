package com.pmApplication.API.domain;


public class GenericResponse<T> {
	
	private T data;
	private String message;
	private Integer status;
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public GenericResponse() {
		super();
	}
	
	
	public GenericResponse(T data) {
		super();
		this.data = data;
	}
	public GenericResponse(Integer status,String message,T data) {
		super();
	
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	
	

}
