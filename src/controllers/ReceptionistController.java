package controllers;

import models.entities.Patient;
import models.entities.Priority;
import services.PatientRegisterRequest;
import services.PatientService;

public class ReceptionistController {
	
	private PatientService patientService;
	
	public ReceptionistController() {
		this.patientService = new PatientService();
	}
	
	public Patient registerPatient(PatientRegisterRequest data) throws Exception{
		try {
			return this.patientService.register(data);
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void addPacientToQueue(Patient patient, Priority priority) {
		try {
			this.patientService.addToQueue(patient, priority);
			System.out.println("Paciente " + patient.getName() + " adicionado a fila");
		}catch(RuntimeException e) {
			e.getStackTrace();
		}
	}

}
