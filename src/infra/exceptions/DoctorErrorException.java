package infra.exceptions;

@SuppressWarnings("serial")
public class DoctorErrorException extends Exception {

	public DoctorErrorException(String message) {
		super(message);
	}
	
}
