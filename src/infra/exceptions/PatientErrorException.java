package infra.exceptions;

@SuppressWarnings("serial")
public class PatientErrorException extends Exception {
	
	public PatientErrorException(String message) {
		super(message);
	}
	
}	
