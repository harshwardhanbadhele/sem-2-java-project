package com.university.model;
public class PrerequisiteNotMetException extends Exception {
	private static final long serialVersionUID = 1L;

	public PrerequisiteNotMetException(String message) {
        super(message);
    }
}