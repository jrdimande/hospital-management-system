package controllers;

import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.Queue.Queue;
import models.entities.Patient;
import models.entities.Priority;
import services.patient.PatientRegisterRequest;
import services.patient.PatientService;

public class ReceptionistController {

	/**
	 * @params patientService - business logic for patient
	 */

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

	public void removePatient(int id){
		try {
			this.patientService.remove(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addPacientToQueue(Patient patient, Priority priority) throws Exception{
		try {
			this.patientService.addToQueue(patient, priority);
			System.out.println("Paciente " + patient.getName() + " adicionado a fila");
		}catch(RuntimeException e) {
			e.getStackTrace();
		}
	}

	public Queue getPatientQueue() {
		return this.patientService.listPatientsQueue();
	}

	public DoubleLinkedList getPatientList(){return this.patientService.getPatients();}

}
