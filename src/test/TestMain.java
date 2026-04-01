package test;

import controllers.PatientController;
import models.entities.Gender;
import models.entities.Patient;
import models.entities.Priority;
import services.PatientRegisterRequest;

public class TestMain {
	
	static PatientController patientController = new PatientController();
	
	public static void main(String[] args) {
		var patient = testRegister();
		testAddToQueue(patient, Priority.LOW);
	}
	
	private static void testAddToQueue(Patient patient, Priority priority) {
		patientController.addPacientToQueue(patient, priority);
	}
	
	private static Patient testRegister() {
		String name = "Marden De Castro";
		Integer age  = 20;
		String phoneNumber = "+258867903680";
		String address = "Khongolote 1 de maio";
		
		PatientRegisterRequest requestData = new PatientRegisterRequest(
				name, age, Gender.MALE, phoneNumber, address);
		
		return patientController.registerPatient(requestData);
	}
	
}
