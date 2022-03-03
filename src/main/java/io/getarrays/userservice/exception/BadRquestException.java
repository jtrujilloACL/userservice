package io.getarrays.userservice.exception;

public class BadRquestException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1932444465440114648L;

	public BadRquestException() {
		super();
	}

	public BadRquestException(String message) {
		super(message);
	}

}
