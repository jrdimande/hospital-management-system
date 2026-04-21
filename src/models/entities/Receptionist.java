package models.entities;

public class Receptionist implements Identifiable, Nameable{
	
	private int id;
	private String name;
	private String phone;
	private String password;
	
	public Receptionist() {}
	
	public Receptionist(String name, String phone, String password) {
		this.password = password;
		this.name = name;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
}
