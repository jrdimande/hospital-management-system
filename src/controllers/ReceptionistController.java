package controllers;

import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.Heap.PriorityQueue;
import models.data_structures.Queue.Queue;
import models.entities.Patient;
import models.entities.Priority;
import services.patient.PatientRegisterRequest;
import services.patient.PatientService;

public class ReceptionistController {

	private PatientService patientService;

	public ReceptionistController() {
		this.patientService = new PatientService();
	}

	public Patient registerPatient(PatientRegisterRequest data) {
		try {
			return this.patientService.register(data);
		}catch(Exception e) {
			e.printStackTrace();
		}return null;
	}

	public void removePatient(int id){
		try {
			this.patientService.remove(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void updatePatient(Patient patient){
		try {
			this.patientService.updatePatient(patient);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void addPacientToQueue(int id, Priority priority) {
		try {
			this.patientService.addToQueue(id, priority);
		}catch(Exception e) {
			e.getStackTrace();
		}
	}

	public int getIdByName(String name){
		return patientService.getIdByName(name);
	}

	public int countHighPriority(){
		return patientService.counHighPriority();
	}

	public Queue getPatientQueue() {
		return this.patientService.listPatientsQueue();
	}

	public DoubleLinkedList getPatientList(){return this.patientService.getPatients();}

	public PriorityQueue getPriorityQueue(){
		return patientService.getPriorityQueue();
	}

}
