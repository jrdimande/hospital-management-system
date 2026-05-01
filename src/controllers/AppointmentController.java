package controllers;

import models.data_structures.Stack.Stack;
import services.appointment.AppointmentService;

public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(){
        appointmentService = new AppointmentService();
    }

    public void removeAppointment(int id){
        appointmentService.removeAppointment(id);
    }

    public Stack getAppointments(){
        return appointmentService.getAppointments();
    }
}
