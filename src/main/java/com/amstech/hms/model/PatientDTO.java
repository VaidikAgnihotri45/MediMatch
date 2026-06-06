package com.amstech.hms.model;

public class PatientDTO {
private int id;
private int user_id;
private String status;


private String first_name;
private String last_name;
private String email;
private String mobile_no;

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

public int getId() {
    return id;
}

public void setId(int id) {
    this.id = id;
}

public int getUser_id() {
    return user_id;
}

public void setUser_id(int user_id) {
    this.user_id = user_id;
}

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}

}
