package infra.exceptions;

@SuppressWarnings("serial")
public class EmptyNameException extends Exception{
	
	public EmptyNameException() {
		super("Patient name cannot be empty");
	}
	
}
