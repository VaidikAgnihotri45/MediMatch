package com.amstech.hms.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.hms.model.PrescriptionDTO;
import com.amstech.hms.util.DButil;

public class PrescriptionDAO {

    private DButil dbUtil;

    public PrescriptionDAO(DButil dbUtil) {
        this.dbUtil = dbUtil;
    }

    // ✅ SAVE
    public void addPrescription(int appointmentId, int doctorId, int patientId,
            String medicines, String notes) throws Exception {

        String sql = "INSERT INTO prescription (appointment_id, doctor_id, patient_id, medicines, notes) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, appointmentId);
        ps.setInt(2, doctorId);
        ps.setInt(3, patientId);
        ps.setString(4, medicines);
        ps.setString(5, notes);

        ps.executeUpdate();
    }

    // ✅ FETCH (patient side)
	
	  public List<PrescriptionDTO> getPrescriptionsByPatientId(int patientId)
	  throws Exception {
	  
	  List<PrescriptionDTO> list = new ArrayList<>();
	  
		/*
		 * String sql = """SELECT p.*, u.first_name AS doctor_name, a.appointment_date
		 * FROM prescription p JOIN user u ON p.doctor_id = u.id JOIN appointment a ON
		 * p.appointment_id = a.id WHERE p.patient_id = ? ORDER BY p.id DESC """;
		 */	  
	  
	  
	  String sql = """
			  SELECT p.*, u.first_name AS doctor_name,a.appointment_date
			  FROM prescription p
			  JOIN user u ON p.doctor_id = u.id
			  JOIN appointment a ON p.appointment_id = a.id
			  WHERE p.patient_id = ?
			  ORDER BY p.id DESC
			  """;
	  
	  
	  PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
	  ps.setInt(1, patientId);
	  
	  ResultSet rs = ps.executeQuery();
	  
	  while (rs.next()) { PrescriptionDTO dto = new PrescriptionDTO();
	  dto.setId(rs.getInt("id"));
	  
	  dto.setMedicines(rs.getString("medicines"));
	  dto.setNotes(rs.getString("notes"));
	  dto.setDoctorName(rs.getString("doctor_name"));
	  
	  dto.setAppointmentDate(rs.getString("appointment_date"));
	  list.add(dto); }
	  
	  return list; }
	 
    
    
	/*
	 * public List<PrescriptionDTO> getPrescriptionsByPatientId(int patientId)
	 * throws Exception {
	 * 
	 * List<PrescriptionDTO> list = new ArrayList<>();
	 * 
	 * String sql = """ SELECT p.*, du.first_name AS doctor_name, pu.first_name AS
	 * patient_name, a.appointment_date FROM prescription p JOIN user du ON
	 * p.doctor_id = du.id JOIN user pu ON p.patient_id = pu.id JOIN appointment a
	 * ON p.appointment_id = a.id WHERE p.patient_id = ? ORDER BY p.id DESC """;
	 * 
	 * PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
	 * ps.setInt(1, patientId);
	 * 
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * while (rs.next()) { PrescriptionDTO dto = new PrescriptionDTO();
	 * 
	 * dto.setId(rs.getInt("id")); dto.setMedicines(rs.getString("medicines"));
	 * dto.setNotes(rs.getString("notes"));
	 * dto.setDoctorName(rs.getString("doctor_name"));
	 * dto.setAppointmentDate(rs.getString("appointment_date"));
	 * 
	 * list.add(dto); }
	 * 
	 * return list; }
	 */
    
    
    public PrescriptionDTO getPrescriptionById(int id) throws Exception {

		/*
		 * String sql = """ SELECT p.*, u.first_name AS doctor_name, u.first_name AS
		 * patient_name, a.appointment_date FROM prescription p JOIN user u ON
		 * p.doctor_id = u.id JOIN appointment a ON p.appointment_id = a.id WHERE p.id =
		 * ? """;
		 */
		  
    	String sql = """
    			SELECT p.*, 
    			du.first_name AS doctor_name,
    			pu.first_name AS patient_name,
    			a.appointment_date

    			FROM prescription p

    			LEFT JOIN doctor d ON p.doctor_id = d.id
    			LEFT JOIN user du ON d.user_id = du.id

    			LEFT JOIN patient pt ON p.patient_id = pt.id
    			LEFT JOIN user pu ON pt.user_id = pu.id

    			JOIN appointment a ON p.appointment_id = a.id

    			WHERE p.id = ?
    			""";
		 
			/*
			 * String sql = """ SELECT p.*, u.first_name AS doctor_name, a.appointment_date
			 * FROM prescription p JOIN user u ON p.doctor_id = u.id JOIN appointment a ON
			 * p.appointment_id = a.id WHERE p.patient_id = ? ORDER BY p.id DESC """;
			 */
        
        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            PrescriptionDTO dto = new PrescriptionDTO();
            dto.setId(rs.getInt("id"));
            dto.setMedicines(rs.getString("medicines"));
            dto.setNotes(rs.getString("notes"));

            dto.setAppointmentDate(rs.getString("appointment_date"));
            dto.setDoctorName(rs.getString("doctor_name"));

            dto.setPatientId(rs.getInt("patient_id"));
            dto.setDoctorId(rs.getInt("doctor_id"));

            dto.setPatientName(rs.getString("patient_name"));
            System.out.println("DB hit for ID: " + id);
            return dto;
        }

        return null;
    }
    public PrescriptionDTO getPrescriptionByAppointmentId(int appointmentId)
            throws Exception {

        PrescriptionDTO p = null;

		/*
		 * String sql = """ SELECT p.*, pu.first_name AS patient_name, du.first_name AS
		 * doctor_name, a.appointment_date
		 * 
		 * FROM prescription p
		 * 
		 * JOIN appointment a ON p.appointment_id = a.id
		 * 
		 * JOIN patient pt ON p.patient_id = pt.id
		 * 
		 * JOIN user pu ON pt.user_id = pu.id
		 * 
		 * JOIN doctor doc ON p.doctor_id = doc.id
		 * 
		 * JOIN user du ON doc.user_id = du.id
		 * 
		 * WHERE p.appointment_id = ? """;
		 */
        String sql = """
        		SELECT 
        		    p.*,
        		    a.status,
        		    pu.first_name AS patient_name,
        		    du.first_name AS doctor_name,
        		    a.appointment_date

        		FROM prescription p

        		JOIN appointment a
        		ON p.appointment_id = a.id

        		JOIN patient pt
        		ON p.patient_id = pt.id

        		JOIN user pu
        		ON pt.user_id = pu.id

        		JOIN doctor doc
        		ON p.doctor_id = doc.id

        		JOIN user du
        		ON doc.user_id = du.id

        		WHERE p.appointment_id = ?
        		""";
        
        

        PreparedStatement ps =
                dbUtil.getConnection().prepareStatement(sql);

        ps.setInt(1, appointmentId);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            p = new PrescriptionDTO();

            p.setId(rs.getInt("id"));
            p.setAppointmentId(rs.getInt("appointment_id"));
            p.setDoctorId(rs.getInt("doctor_id"));
            p.setPatientId(rs.getInt("patient_id"));
            p.setMedicines(rs.getString("medicines"));
            p.setNotes(rs.getString("notes"));
            p.setPatientName(rs.getString("patient_name"));

            p.setDoctorName(rs.getString("doctor_name"));

            p.setAppointmentDate(
                    rs.getString("appointment_date"));
            p.setStatus(rs.getString("status"));
        }

        return p;
    }
}