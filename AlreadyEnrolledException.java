package com.university.model;


public class AlreadyEnrolledException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlreadyEnrolledException(String message) {
        super(message);
    }
}