package com.amstech.hms.model;

public class AdminDTO {
	private int id;
	private int patient_id;
	private int doctor_id;

	private int user_id;
	private int appointment_id;
	private  String shift_time;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(int patient_id) {
		this.patient_id = patient_id;
	}
	public int getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getAppointment_id() {
		return appointment_id;
	}
	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
	public String getShift_time() {
		return shift_time;
	}
	public void setShift_time(String shift_time) {
		this.shift_time = shift_time;
	}
	

	

	
	

}
