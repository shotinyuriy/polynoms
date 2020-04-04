package com.shotin.rest.webservices;

import java.util.Date;

public class HelloWorldBean {

	private String message;
	private Date date;

	public HelloWorldBean(String message) {
		this.message = message;
		this.date = new Date();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return String.format("HelloWorldBean [message=%s]", message);
	}

	public Date getDate() {
		return date;
	}
}
