package com.example.rewards.exception;

public class ResourseNotFoundException extends RuntimeException {
	private String message;

	public ResourseNotFoundException() {
	}

	public ResourseNotFoundException(String msg) {
		super(msg);
		this.message = msg;
	}
}