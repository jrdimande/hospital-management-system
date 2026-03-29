package models.entities;

public class Nurse implements Identifiable, Nameable{
    private int id;
    private String name;
    private String shift;

    public Nurse(String name, String shift) {
        this.id = 0;
        this.name = name;
        this.shift = shift;
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

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }
}
