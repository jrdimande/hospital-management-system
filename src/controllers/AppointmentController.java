package controllers;

import models.data_structures.Stack.Stack;
import services.appointment.AppointmentService;

public class AppointmentController {
    private AppointmentService appointmentService;

    public AppointmentController(){
        appointmentService = new AppointmentService();
    }

    public Stack getAppointments(){
        return appointmentService.getAppointments();
    }
}
