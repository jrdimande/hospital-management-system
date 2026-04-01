package controllers;

import services.PatientRegisterRequest;
import services.PatientService;

public class PatientController {
	
	private PatientService patientService;;
	
	public PatientController() {
		this.patientService = new PatientService();
	}
	
	public void registerPatient(PatientRegisterRequest data) {
		try {
			this.patientService.register(data);
			System.out.println("Paciente " + data.getName() +" registrado");
		}catch(RuntimeException e) {
			e.getStackTrace();
		}
	}

}
