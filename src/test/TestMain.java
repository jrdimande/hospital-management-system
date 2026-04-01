package test;

import controllers.PatientController;
import models.entities.Gender;
import models.entities.Priority;
import services.PatientRegisterRequest;

public class TestMain {
	
	public static void main(String[] args) {
		testRegister();
	}
	
	private static void testRegister() {
		PatientController patientController = new PatientController();
		
		String name = "Marden De Castro";
		Integer age  = 20;
		String phoneNumber = "+258867903680";
		String address = "Khongolote 1 de maio";
		
		PatientRegisterRequest requestData = new PatientRegisterRequest(
				name, age, Gender.MALE, phoneNumber, address, Priority.LOW);
		
		patientController.registerPatient(requestData);
	}
	
}
