package controllers;

import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.entities.Doctor;
import repositories.DoctorRepository;
import services.doctor.DoctorResgisterRequest;
import services.doctor.DoctorService;

public class DoctorController {
	
	private DoctorService doctorService;
	private DoctorRepository doctorRepository;

	public DoctorController(){
		doctorService = new DoctorService();
	}
	
	public void register(DoctorResgisterRequest data) {
		try {
			this.doctorService.registerDoctor(data);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void removeDocter(int id){
		try{
			doctorService.removeDoctor(id);
		}catch(Exception e){
			throw e;
		}
	}

	public void updateDoctor(Doctor doctor){
		try {
			doctorService.updateDoctor(doctor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void check(String patient, String doctor, String note) {
		try {
			doctorService.checkPatitient(patient, doctor, note);
		}catch(Exception e) {
			e.printStackTrace();

		}
	}

	public DoubleLinkedList loadDoctors(){
		return doctorService.getDoctors();
	}

}
