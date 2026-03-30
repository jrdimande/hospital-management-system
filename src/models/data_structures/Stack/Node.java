package models.data_structures.Stack;

import models.entities.Appointment;

public class Node {
    private Appointment appointment;
    private Node next;

    public Node(Appointment appointment){
        this.appointment = appointment;
        this.next = null;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
