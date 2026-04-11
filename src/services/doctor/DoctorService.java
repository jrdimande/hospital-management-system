package services.doctor;

import infra.exceptions.DoctorErrorException;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.Queue.Queue;
import models.data_structures.Stack.Stack;
import models.entities.Appointment;
import models.entities.Doctor;
import models.entities.History;

public class DoctorService {
	
	private DoubleLinkedList doctors;
	private Queue patientsQueue;
	private Stack appointments;
	
	
	public Doctor registerDoctor(DoctorResgisterRequest data) throws Exception{
		Doctor doctor = new Doctor(data.getName(),
				data.getSpeciality(),
				data.getPhoneNumber(),
				null);
		
		if(this.doctors.contain(doctor.getId())) {
			throw new DoctorErrorException("");
		}
		
		this.doctors.add(doctor);
		return doctor;
	}
	
	public Appointment checkPatitient(Doctor doctor, String notes) throws Exception{
		var patient = this.patientsQueue.peek();
		
		var appointment = new Appointment(patient, doctor, notes);
		
		this.appointments.push(appointment);
		this.patientsQueue.dequeue();
		
		return appointment;
	}
	
}
