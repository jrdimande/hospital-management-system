package services.appointment;

import models.data_structures.Stack.Stack;
import repositories.AppointmentRepository;

public class AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentService(){
        appointmentRepository = new AppointmentRepository();

    }

    public Stack getAppointments(){
        return appointmentRepository.findAll();
    }
}
