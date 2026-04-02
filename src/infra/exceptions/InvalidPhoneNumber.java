package infra.exceptions;

@SuppressWarnings("serial")
public class InvalidPhoneNumber extends Exception{

	public InvalidPhoneNumber(String message) {
		super(message);
	}
}
