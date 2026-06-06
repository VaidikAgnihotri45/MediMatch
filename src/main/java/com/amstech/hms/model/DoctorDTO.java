package com.amstech.hms.model;

public class DoctorDTO {

    private int id;
    private String specialization;
    private String status;
    private int user_id;
    private String shift_time;
    private String first_name;
    private String last_name;
    private String email;
    private String mobile_no;
    private String doctor_name;
    private String doctor_email;
    

    public String getDoctor_email() {
		return doctor_email;
	}
	public void setDoctor_email(String doctor_email) {
		this.doctor_email = doctor_email;
	}
	public String getDoctor_name() { return doctor_name; }
    public void setDoctor_name(String doctor_name) { this.doctor_name = doctor_name; }
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getShift_time() {
		return shift_time;
	}
	public void setShift_time(String shift_time) {
		this.shift_time = shift_time;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

   
}