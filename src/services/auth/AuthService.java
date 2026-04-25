package services.auth;

import models.entities.Doctor;
import models.entities.Receptionist;
import repositories.DoctorRepository;
import repositories.ReceptionistRepository;
import com.fazecast.jSerialComm.SerialPort;

public class AuthService {

	private ReceptionistRepository receptionistRepository;
	private DoctorRepository doctorRepository;
	private SerialPort port;
	private final String ALLOWED_UID = "D0B7DA6";

	public AuthService() {
		this.receptionistRepository = new ReceptionistRepository();
		this.doctorRepository = new DoctorRepository();
	}


	public Doctor loginDoctor(LoginRequestData data) {
		return doctorRepository.login(data);
	}
	public Receptionist loginReceptionist(LoginRequestData data) {
		return receptionistRepository.login(data);
	}

	public boolean startRFID(){
		port = SerialPort.getCommPort("/dev/ttyACM0");
		port.setBaudRate(9600);

		if (!port.openPort()){
			System.out.println("Ocorreu um erro ao abrir porta");
			return false;
		}
		return false;
	}

	public boolean validateRFID(String uid) {

		if (uid == null) return false;

		uid = uid.replace(" ", "")
				.replace(":", "")
				.replace("\n", "")
				.replace("\r", "");

		return uid.contains(ALLOWED_UID);
	}





}
