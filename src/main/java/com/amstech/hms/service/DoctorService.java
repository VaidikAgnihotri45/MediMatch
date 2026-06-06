package com.amstech.hms.service;

import java.util.List;

import com.amstech.hms.Dao.DoctorDAO;
import com.amstech.hms.model.DoctorDTO;

public class DoctorService {

    private DoctorDAO doctorDAO;

    public DoctorService(DoctorDAO doctorDAO) {

        this.doctorDAO = doctorDAO;
    }

	/*
	 * public List<DoctorDTO> DoctorList() throws Exception {
	 * 
	 * return doctorDAO.getAllDoctorsFull(); }
	 */
    public List<DoctorDTO> DoctorList(String search) throws Exception {
        return doctorDAO.getAllDoctorsFull(search);
    }
}