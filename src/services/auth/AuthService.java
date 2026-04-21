package services.auth;

import repositories.DoctorRepository;
import repositories.ReceptionistRepository;

public class AuthService {
	
	private ReceptionistRepository receptionistRepository;
	private DoctorRepository doctorRepository;
	
	public AuthService() {
		this.receptionistRepository = new ReceptionistRepository();
		this.doctorRepository = new DoctorRepository();
	}
	
	/**
	 * @param view 'e para controlar quem esta a pedir o login
	 */
	public Object login(LoginRequestData data, View view) {
		switch (view) {
		case DOCTOR: 
			return this.doctorRepository.login(data);
		case RECEPTIONIST:
			return this.receptionistRepository.login(data);
		default:
			throw new IllegalArgumentException("Unknown view: " + view);
		}
	}

}
