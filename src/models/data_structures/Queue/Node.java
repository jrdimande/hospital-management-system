package models.data_structures.Queue;

import models.entities.Patient;

public class Node {
    private Patient patient;
    private Node next;

    public Node(Patient patient){
        this.patient = patient;
        this.next = null;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
