package models.entities;

import java.time.LocalDate;

public class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private String date;
    private String notes;
    private boolean completed;

    public Appointment(Patient patient, Doctor doctor, String notes) {
        this.id = 0;
        this.patient = patient;
        this.doctor = doctor;
        this.date = LocalDate.now().toString();
        this.notes = notes;
        this.completed = false;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isCompleted() {
        return completed;
    }



    public void setStatus(boolean status) {
        this.completed = status;
    }
}
