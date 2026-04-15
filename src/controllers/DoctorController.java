package controllers;

import models.entities.Doctor;
import services.doctor.DoctorResgisterRequest;
import services.doctor.DoctorService;

public class DoctorController {
	
	private DoctorService doctorService;
	
	public void register(DoctorResgisterRequest data) throws Exception{
		try {
			this.doctorService.registerDoctor(data);
		}catch(Exception e) {
			throw e;
		}
	}

	public void removeDocter(int id){
		try{
			doctorService.removeDoctor(id);
		}catch(Exception e){
			throw e;
		}
	}
	
	public void check(Doctor doctor, String note) {
		try {
			this.check(doctor, note);
		}catch(Exception e) {
			throw e;
		}
	}

}
