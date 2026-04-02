package infra.exceptions;

@SuppressWarnings("serial")
public class EmptyGenderException extends Exception {
	
	public EmptyGenderException() {
		super("Gender cannot be empty");
	}

}
