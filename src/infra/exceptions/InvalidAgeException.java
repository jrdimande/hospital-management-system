package infra.exceptions;

@SuppressWarnings("serial")
public class InvalidAgeException extends Exception {
	
	public InvalidAgeException(String message) {
		super(message);
	}

}
