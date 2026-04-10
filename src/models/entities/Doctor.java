package models.entities;

public class Doctor implements Identifiable, Nameable{
    private int id;
    private String name;
    private String speciality;
    private String phoneNumber;
    private String password;

    public Doctor(String name, String speciality, String phoneNumber, String password){
        this.id = 0;
        this.name = name;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }
    
    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
