package com.MEDIADEMO.demo;

public class CustomerException extends RuntimeException {

	private final String message;

	public CustomerException() {
		this.message = "Customer Exception";
	}

	public CustomerException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
