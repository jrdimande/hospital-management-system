package services;

import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.entities.Patient;

public class PatientService {
	
	private DoubleLinkedList patients;
	
	public PatientService() {
		this.patients = new DoubleLinkedList();
	}
	
	public Patient register(PatientRegisterRequest data) {
		this.verifyUserData(data);
		var patient = new Patient(data.getName(),
				data.getAge(),
				data.getGender(),
				data.getPhoneNumber(),
				data.getAddress(),
				data.getPriority());
		
		patients.add(patient);
		return patient;
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
		
		if(data.getPriority() == null) {
			throw new RuntimeException("The priority cannot be null");
		}
	}
}
