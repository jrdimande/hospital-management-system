package test;

import controllers.ReceptionistController;
import models.entities.Gender;
import models.entities.Patient;
import models.entities.Priority;
import services.history.HistoryService;
import services.patient.PatientRegisterRequest;

public class TestMain {
	
	static ReceptionistController patientController = new ReceptionistController();
	
	public static void main(String[] args) {
		try {
			testHistory();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean test(String phoneNumber) {
		return phoneNumber.matches("^+(258)(82|83|84|85|86|87)[0-9]{7}$");
	}
	
	private static void testAddToQueue(Patient patient, Priority priority) throws Exception{
		//patientController.addPacientToQueue(patient, priority);
	}
	
	private static Patient testRegister() throws Exception{
		String name = "Marden De Castro";
		Integer age  = 20;
		String phoneNumber = "258867903680";
		String address = "Khongolote 1 de maio";
		
		PatientRegisterRequest requestData = new PatientRegisterRequest(
				name, age, Gender.MALE, phoneNumber, address);
		
		return patientController.registerPatient(requestData);
	}
	
	//history test
	private static void testHistory() throws Exception{
		HistoryService historyService = new HistoryService();
		
		System.out.println(historyService.listHistory().peek());
	}
	
}
