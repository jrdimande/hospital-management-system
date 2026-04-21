package services.doctor;

import infra.exceptions.DoctorErrorException;
import models.data_structures.DoubleLinkedList.DoubleLinkedList;
import models.data_structures.Queue.Queue;
import models.entities.Appointment;
import models.entities.Doctor;
import models.entities.HistoryType;
import models.entities.Patient;
import repositories.DoctorRepository;
import services.history.HistoryService;

public class DoctorService {
	
	private DoubleLinkedList doctors;
	private Queue patientsQueue;
	private HistoryService historyService;
	private DoctorRepository doctorRepository;

	public DoctorService(){
		this.doctors = doctorRepository.findAll();
		this.doctorRepository = new DoctorRepository();
		this.historyService = new HistoryService();
		this.patientsQueue = new Queue();
	}
	
	public Doctor registerDoctor(DoctorResgisterRequest data) throws Exception{
		Doctor doctor = new Doctor(data.getName(),
				data.getSpeciality(),
				data.getPhoneNumber(),
				data.getPassword());

		
		if(this.doctors.contain(doctor.getId())) {
			throw new DoctorErrorException("ID already exists");
		}

		int id = doctorRepository.save(data);
		doctor.setId(id);

		this.doctors.add(doctor);
		
		this.historyService.addToHistory("Registration: Doctor - " + doctor.getName(), HistoryType.REGISTRATION);
		return doctor;
	}

	public void updateDoctor(Doctor doctor){
		doctorRepository.update(doctor);
		this.historyService.addToHistory("Update: Doctor - " + doctor.getName(), HistoryType.UPDATE);
	}

	public void removeDoctor(int id) {
		if(this.doctors.contain(id)) {
			this.doctors.removeByID(id);
			doctorRepository.delete(id);
		}
	}
	
	public Appointment checkPatitient(Doctor doctor, String notes) throws Exception{
		Patient patient = this.patientsQueue.peek();
		
		Appointment appointment = new Appointment(patient.getName(), doctor.getName(), notes);
		
		this.historyService.addToHistory("Appointment: Doctor - " + doctor.getName() + " | Patient - " + patient.getName(), HistoryType.APPOINTMENT);
		this.patientsQueue.dequeue();
		
		return appointment;
	}
	
}
