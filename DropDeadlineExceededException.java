package com.university.model;

public class DropDeadlineExceededException extends Exception {
	private static final long serialVersionUID = 1L;

	public DropDeadlineExceededException(String message) {
        super(message);
    }
}