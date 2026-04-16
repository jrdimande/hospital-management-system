package models.entities;

import java.time.LocalDate;

public class Appointment implements Identifiable {
    private int id;
    private String patient;
    private String doctor;
    private String date;
    private String notes;
    private boolean completed;

    public Appointment(){}

    public Appointment(String patient, String doctor, String notes) {
        this.id = 0;
        this.patient = patient;
        this.doctor = doctor;
        this.date = LocalDate.now().toString();
        this.notes = notes;
        this.completed = false;

    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
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
