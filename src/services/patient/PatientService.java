package services.patient;

import javax.naming.InvalidNameException;

import infra.exceptions.EmptyAddressException;
import infra.exceptions.EmptyGenderException;
import infra.exceptions.InvalidAgeException;
import infra.exceptions.InvalidPhoneNumber;
import infra.exceptions.PatientErrorException;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.Queue.Queue;
import models.entities.Patient;
import models.entities.Priority;
import repositories.PatientRepository;

public class PatientService {
	
	private DoubleLinkedList patients;
	private Queue queue;
	private PatientRepository patientRepository = new PatientRepository();
	
	public PatientService() {
		this.patients = new DoubleLinkedList();
		this.queue = new Queue();
	}
	
	public Patient register(PatientRegisterRequest data) throws Exception{
		this.verifyUserData(data);
		patientRepository.save(data);
		Patient patient = new Patient(data.getName(),
				data.getAge(),
				data.getGender(),
				data.getPhoneNumber(),
				data.getAddress());
		
		if(this.patients.contain(patient.getId())) {			
			throw new PatientErrorException("User already exists");
		}
		
		patients.add(patient);
		return patient;
	}
	
	public Patient addToQueue(Patient patient, Priority priority) throws Exception{
		if(patient == null) {
			throw new RuntimeException("Please select a patient");
		}
		
		if(priority == null) {
			throw new RuntimeException("Please set a priority");
		}
		
		if(!patients.contain(patient.getId())) {
			throw new PatientErrorException("Patient not registred");
		}
		
		patient.setPriority(priority);
		this.queue.enqueue(patient);
		
		return patient;
	}

	public void cancel(){
		this.queue.dequeue();
	}

	
	public Queue listPatientsQueue(){
		return this.queue;
	}
	
	private void verifyUserData(PatientRegisterRequest data) throws Exception{
		if(data.getAge() == null) {
			throw new InvalidAgeException("Age cannot be empty");
		}
		
		if(data.getAge() < 0 || data.getAge() > 100) {
			throw new InvalidAgeException("Invalid age!");
		}
		
		if(data.getName() == null) {
			throw new InvalidNameException();
		}
		
		if(data.getGender() == null) {
			throw new EmptyGenderException();
		}
		
		if(data.getAddress() == null) {
			throw new EmptyAddressException();
		}
		
		if(data.getPhoneNumber() == null) {
			throw new InvalidPhoneNumber("Phone number cannot be null");
		}
		
		if(!data.getPhoneNumber().matches("^(258)(82|83|84|85|86|87)[0-9]{7}$")) {
			throw new InvalidPhoneNumber("Invalid phone number");
		}
	}
}
