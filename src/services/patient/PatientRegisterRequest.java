package services.patient;

import models.entities.Gender;

public class PatientRegisterRequest {
	private int id;
	private String name;
	private Integer age;
	private Gender gender;
	private String phoneNumber;
	private String address;
	
	public PatientRegisterRequest() {}
	
	public PatientRegisterRequest(String name, Integer age,
			Gender gender, String phoneNumber, String address) {
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.age = age;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumner) {
		this.phoneNumber = phoneNumner;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
