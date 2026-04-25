package services.patient;

import javax.naming.InvalidNameException;

import infra.exceptions.EmptyAddressException;
import infra.exceptions.EmptyGenderException;
import infra.exceptions.InvalidAgeException;
import infra.exceptions.InvalidPhoneNumber;
import infra.exceptions.PatientErrorException;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.Queue.Node;
import models.data_structures.Queue.Queue;
import models.entities.HistoryType;
import models.entities.Patient;
import models.entities.Priority;
import repositories.PatientRepository;
import services.history.HistoryService;

public class PatientService {
	
	private DoubleLinkedList patients;
	private Queue queue;
	private PatientRepository patientRepository;
	
	private HistoryService historyService;
	
	public PatientService() {
		this.patientRepository = new PatientRepository();
		this.patients = patientRepository.findAll();
		this.queue = new Queue();
		this.historyService = new HistoryService();
	}
	
	public Patient register(PatientRegisterRequest data) throws Exception{
		this.verifyUserData(data);
		int id = patientRepository.save(data);
		data.setId(id);

		Patient patient = new Patient(data.getName(),
				data.getAge(),
				data.getGender(),
				data.getPhoneNumber(),
				data.getAddress());
		patient.setId(id);
		patients.add(patient);
		
		if(this.patients.contain(patient.getId())) {			
			throw new PatientErrorException("User already exists");
		}

		this.historyService.addToHistory("Registration: Patient - " + patient.getName(), HistoryType.REGISTRATION);
		return patient;
	}

	public void remove(int id){
		if (patients.contain(id)){
			patientRepository.delete(id);
			patients.removeByID(id);
		}
	}
	
	public Patient addToQueue(int id, Priority priority) throws Exception{
		Patient patient = patients.getPatient(id);
		if(patient == null) {
			throw new RuntimeException("Please select a patient");
		}
		
		if(priority == null) {
			throw new RuntimeException("Please set a priority");
		}
		
		if(!patients.contain(patient.getId())) {
			throw new PatientErrorException("Patient not registred");
		}

		if (queue.contain(id)){
			throw new IllegalArgumentException("Paciente ja esta na fila");
		}
		
		patient.setPriority(priority);
		this.queue.enqueue(patient);
		
		return patient;
	}

	public Queue listPatientsQueue(){
		return this.queue;
	}

	public DoubleLinkedList getPatients(){
		return this.patients;
	}

	public void updatePatient(Patient patient){
		this.historyService.addToHistory("Update: Doctor - " + patient.getName(), HistoryType.UPDATE);
		patientRepository.update(patient);
	}

	public int counHighPriority(){
		if (queue.getHead() == null){
			return 0;
		}

		Node current = queue.getHead();
		int counter = 0;

		while (current != null){
			if (current.getPatient().getPriority() == Priority.HIGH){
				counter++;
			}
			current = current.getNext();
		}
		return counter;

	}

	public int getIdByName(String name){
		return patientRepository.getIdByName(name);
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
		
		if(!data.getPhoneNumber().matches("^(82|83|84|85|86|87)[0-9]{7}$")) {
			throw new InvalidPhoneNumber("Invalid phone number");
		}
	}
}
