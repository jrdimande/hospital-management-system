package services.doctor;

import infra.exceptions.DoctorErrorException;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.Queue.Queue;
import models.data_structures.Stack.Stack;
import models.entities.Appointment;
import models.entities.Doctor;
import models.entities.History;
import models.entities.Patient;
import repositories.DoctorRepository;

public class DoctorService {
	
	private DoubleLinkedList doctors;
	private Queue patientsQueue;
	private Stack appointments;
	private DoctorRepository doctorRepository;

	public DoctorService(){
		this.doctors = doctorRepository.findAll();
		this.doctorRepository = new DoctorRepository();
	}
	
	public Doctor registerDoctor(DoctorResgisterRequest data) throws Exception{
		Doctor doctor = new Doctor(data.getName(),
				data.getSpeciality(),
				data.getPhoneNumber(),
				data.getPassword());

		
		if(this.doctors.contain(doctor.getId())) {
			throw new DoctorErrorException("");
		}

		int id = doctorRepository.save(data);
		doctor.setId(id);

		this.doctors.add(doctor);
		return doctor;
	}

	public void updateDoctor(Doctor doctor){
		doctorRepository.update(doctor);

	}

	public void removeDoctor(int id){
		doctorRepository.delete(id);
	}
	
	public Appointment checkPatitient(Doctor doctor, String notes) throws Exception{
		Patient patient = this.patientsQueue.peek();
		
		Appointment appointment = new Appointment(patient.getName(), doctor.getName(), notes);
		
		this.appointments.push(appointment);
		this.patientsQueue.dequeue();
		
		return appointment;
	}
	
}
