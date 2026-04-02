package infra.exceptions;

@SuppressWarnings("serial")
public class EmptyAddressException extends Exception{

	public EmptyAddressException() {
		super("Address cannot be empty");
	}
}
