package services;

import java.util.List;

import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.Queue.Queue;
import models.entities.Patient;
import models.entities.Priority;

public class PatientService {
	
	private DoubleLinkedList patients;
	private Queue queue; 
	
	public PatientService() {
		this.patients = new DoubleLinkedList();
		this.queue = new Queue();
	}
	
	public Patient register(PatientRegisterRequest data) {
		this.verifyUserData(data);
		var patient = new Patient(data.getName(),
				data.getAge(),
				data.getGender(),
				data.getPhoneNumber(),
				data.getAddress());
		
		patients.add(patient);
		return patient;
	}
	
	public Patient addToQueue(Patient patient, Priority priority) {
		if(priority == null) {
			throw new RuntimeException("Please set a priority");
		}
		
		if(patient == null) {
			throw new RuntimeException("Please select a patient");
		}
		
		patient.setPriority(priority);
		this.queue.enqueue(patient);
		
		return patient;
	}
	
	
	//list method not implemented yet
	public List<Patient> listPatients(){
		return null;
	}

	
	private void verifyUserData(PatientRegisterRequest data) {
		if(data.getAge() == null) {
			throw new RuntimeException("The age cannot be null");
		}
		
		if(data.getName() == null) {
			throw new RuntimeException("The name cannot be null");
		}
		
		if(data.getGender() == null) {
			throw new RuntimeException("The gender cannot be null");
		}
		
		if(data.getAddress() == null) {
			throw new RuntimeException("The address cannot be null");
		}
		
		if(data.getPhoneNumber() == null) {
			throw new RuntimeException("The phone number cannot be null");
		}
	}
}
