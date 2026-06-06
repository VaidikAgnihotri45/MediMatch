package com.amstech.hms.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.hms.model.AppointmentDTO;
import com.amstech.hms.util.DButil;

public class AppointmentDAO {

	private DButil dbUtil;

	public AppointmentDAO(DButil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public int bookAppointment(AppointmentDTO dto) throws Exception {

		Connection con = dbUtil.getConnection();

		String sql = "INSERT INTO appointment "
				+ "(patient_id,doctor_id,appointment_date,appointment_time,reason,status) " + "VALUES (?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, dto.getPatient_id());
		ps.setInt(2, dto.getDoctor_id());

		ps.setString(3, dto.getAppointment_date());
		ps.setString(4, dto.getAppointment_time());
		ps.setString(5, dto.getReason());
		ps.setString(6, dto.getStatus());

		int count = ps.executeUpdate();

		return count;
	}

	public List<AppointmentDTO> getPendingAppointmentsByDoctor(int doctorId) throws Exception {

		List<AppointmentDTO> list = new ArrayList<>();
		/*
		 * String sql = """ SELECT a.*, u.first_name, u.email, u.mobile_no FROM
		 * appointment a JOIN user u ON a.patient_id = u.id WHERE a.doctor_id = ? AND
		 * a.status = 'PENDING' """;
		 */
		String sql = """
				    SELECT a.*, u.first_name, u.email, u.mobile_no
				    FROM appointment a
				    JOIN patient p ON a.patient_id = p.id
				    JOIN user u ON p.user_id = u.id
				    WHERE a.doctor_id = ? AND a.status = 'PENDING'
				""";

		try (Connection con = dbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, doctorId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				AppointmentDTO a = new AppointmentDTO();

				a.setId(rs.getInt("id"));
				a.setPatientName(rs.getString("first_name"));
				a.setEmail(rs.getString("email"));
				a.setMobile_no(rs.getString("mobile_no"));
				a.setAppointment_date(rs.getString("appointment_date"));
				a.setStatus(rs.getString("status"));

				list.add(a);
			}
		}

		return list;
	}

	public List<AppointmentDTO> getAllAppointments() throws Exception {

		Connection con = dbUtil.getConnection();

		List<AppointmentDTO> list = new ArrayList<>();

	//	String sql = "SELECT * FROM appointment ORDER BY id DESC";
		
	/*
	 * String sql = """ SELECT a.*, u.first_name, u.last_name FROM appointment a
	 * JOIN user u ON a.patient_id = u.id ORDER BY a.id DESC """;
	 */
		
		
	/*
	 * String sql = """ SELECT a.*, CONCAT(pu.first_name, ' ', pu.last_name) AS
	 * patient_name, CONCAT(du.first_name, ' ', du.last_name) AS doctor_name FROM
	 * appointment a JOIN user pu ON a.patient_id = pu.id JOIN doctor d ON
	 * a.doctor_id = d.id JOIN user du ON d.user_id = du.id ORDER BY a.id DESC """;
	 */
		String sql = """
			    SELECT a.*,
			           CONCAT(pu.first_name, ' ', pu.last_name) AS patient_name,
			           CONCAT(du.first_name, ' ', du.last_name) AS doctor_name
			    FROM appointment a
			    JOIN patient p ON a.patient_id = p.id
			    JOIN user pu ON p.user_id = pu.id
			    JOIN doctor d ON a.doctor_id = d.id
			    JOIN user du ON d.user_id = du.id
			    ORDER BY 
			        CASE 
			            WHEN a.status = 'PENDING' THEN 1
			            WHEN a.status = 'APPROVED' THEN 2
			            WHEN a.status = 'COMPLETED' THEN 3
			            ELSE 4
			        END,
			        a.id DESC
			""";
		
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			AppointmentDTO dto = new AppointmentDTO();

			dto.setId(rs.getInt("id"));
			dto.setDoctor_id(rs.getInt("doctor_id"));
			dto.setPatient_id(rs.getInt("patient_id"));
			dto.setAppointment_date(rs.getString("appointment_date"));
			dto.setAppointment_time(rs.getString("appointment_time"));
			dto.setReason(rs.getString("reason"));
			dto.setStatus(rs.getString("status"));
			dto.setPatientName(rs.getString("patient_name"));
			dto.setDoctorName(rs.getString("doctor_name"));
			

			list.add(dto);
		}

		return list;
	}

	public int updateAppointmentStatus(int id, String status) throws Exception {

		Connection con = dbUtil.getConnection();

		String sql = "UPDATE appointment SET status=? WHERE id=?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, status);
		ps.setInt(2, id);

		return ps.executeUpdate();

	}

	public int getTotalAppointments() throws Exception {

		Connection con = dbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM appointment";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1);
		}

		return 0;
	}

	public int getPendingAppointments() throws Exception {

		Connection con = dbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM appointment WHERE status='PENDING'";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1);
		}

		return 0;
	}

	public int getDoctorTotalAppointments(int doctorId) throws Exception {

		Connection con = dbUtil.getConnection();

//		String sql = "SELECT COUNT(*) FROM appointment WHERE doctor_id=? AND status='PENDING'";
	    String sql = "SELECT COUNT(*) FROM appointment WHERE doctor_id = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, doctorId);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1);
		}

		return 0;
	}

	public int getDoctorPendingAppointments(int doctorId) throws Exception {

		Connection con = dbUtil.getConnection();

		String sql = "SELECT COUNT(*) FROM appointment WHERE doctor_id=? AND status='PENDING'";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, doctorId);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return rs.getInt(1);
		}

		return 0;
	}

	public List<AppointmentDTO> getAppointmentsByDoctorId(int doctorId) throws Exception {

		Connection con = dbUtil.getConnection();

		List<AppointmentDTO> list = new ArrayList<>();

		// String sql = "SELECT * FROM appointment WHERE doctor_id=? ORDER BY id DESC";
		/*
		 * String sql= "SELECT a.*, u.first_name, u.last_name " + "FROM appointment a "
		 * + "JOIN patient p ON a.patient_id = p.id " +
		 * "JOIN user u ON p.user_id = u.id " +
		 * "WHERE a.doctor_id=? ORDER BY a.id DESC";
		 */

		/*
		 * String sql ="SELECT a.*, u.first_name, u.last_name, u.email, u.mobile_no " +
		 * "FROM appointment a "+ "LEFT JOIN user u ON a.patient_id = u.id " +
		 * "WHERE a.doctor_id = ? " + "ORDER BY a.id DESC" ;
		 */

		/*
		 * String sql = "SELECT a.*, u.first_name, u.last_name, u.email, u.mobile_no " +
		 * "FROM appointment a " + // "LEFT JOIN patient p ON a.patient_id = p.id " +
		 * "LEFT JOIN user u ON p.user_id = u.id " + "WHERE a.doctor_id = ? " +
		 * "ORDER BY a.id DESC";
		 */

		/*
		 * String sql = """ SELECT a.*, u.first_name, u.last_name, u.email, u.mobile_no
		 * FROM appointment a JOIN patient p ON a.patient_id = p.id JOIN user u ON
		 * p.user_id = u.id WHERE a.doctor_id = ? ORDER BY a.id DESC """;
		 */

		/*
		 * String sql = """ SELECT a.*, u.first_name, u.last_name, u.email, u.mobile_no
		 * FROM appointment a JOIN user u ON a.patient_id = u.id WHERE a.doctor_id = ?
		 * ORDER BY a.id DESC """;
		 */

		String sql = """
				    SELECT a.*,
				           u.first_name, u.last_name,
				           u.email, u.mobile_no
				    FROM appointment a
				    JOIN patient p ON a.patient_id = p.id
				    JOIN user u ON p.user_id = u.id
				    WHERE a.doctor_id = ?
				    ORDER BY a.id DESC
				""";

		/*
		 * String sql = """ SELECT a.*, d.specialization, u.first_name, u.last_name,
		 * u.email, u.mobile_no FROM appointment a JOIN patient p ON a.patient_id = p.id
		 * LEFT JOIN doctor d ON a.doctor_id = d.id LEFT JOIN user u ON d.user_id = u.id
		 * WHERE p.user_id = ? ORDER BY a.id DESC """;
		 */

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, doctorId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			AppointmentDTO dto = new AppointmentDTO();

			dto.setId(rs.getInt("id"));
			dto.setDoctor_id(rs.getInt("doctor_id"));
			dto.setPatient_id(rs.getInt("patient_id"));
			dto.setAppointment_date(rs.getString("appointment_date"));
			dto.setAppointment_time(rs.getString("appointment_time"));
			dto.setReason(rs.getString("reason"));
			dto.setStatus(rs.getString("status"));
			dto.setPatientName(rs.getString("first_name")
					+ (rs.getString("last_name") != null ? " " + rs.getString("last_name") : ""));
			// dto.setPatientName(rs.getString("first_name") + " " +
			// rs.getString("last_name"));
			dto.setEmail(rs.getString("email"));
			dto.setMobile_no(rs.getString("mobile_no"));
			list.add(dto);
		}
		System.out.println("[DAO] Doctor Appointments with Patient = " + list.size());

		return list;
	}

	public boolean isSlotBooked(int doctorId, String date, String time) throws Exception {

		boolean booked = false;

		/*
		 * String sql = """ SELECT COUNT(*) FROM appointment WHERE doctor_id = ? AND
		 * appointment_date = ? AND appointment_time = ? AND status != 'Cancelled' """;
		 */

		/*
		 * String sql = """ SELECT COUNT(*) FROM appointment WHERE doctor_id = ? AND
		 * appointment_date = ? AND appointment_time = ? AND status IS NOT NULL AND
		 * LOWER(status) != 'cancelled' """;
		 */

		String sql = """
				    SELECT COUNT(*)
				    FROM appointment
				    WHERE doctor_id = ?
				    AND appointment_date = ?
				    AND appointment_time = ?
				    AND LOWER(status) IN ('pending', 'approved')
				""";

		try (Connection con = dbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, doctorId);
			ps.setString(2, date);
			ps.setString(3, time);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				booked = rs.getInt(1) > 0;
			}
		}

		return booked;
	}

	public AppointmentDTO getAppointmentById(int id) throws Exception {

		Connection con = dbUtil.getConnection();

		String sql = "SELECT * FROM appointment WHERE id=?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			AppointmentDTO dto = new AppointmentDTO();

			dto.setId(rs.getInt("id"));
			dto.setDoctor_id(rs.getInt("doctor_id"));
			dto.setPatient_id(rs.getInt("patient_id"));
			dto.setStatus(rs.getString("status"));

			return dto;
		}

		return null;
	}

	public List<AppointmentDTO> getAppointmentsByPatientId(int patientId) throws Exception {

		List<AppointmentDTO> list = new ArrayList<>();

		String sql = """
				    SELECT a.*, d.specialization, u.first_name
				    FROM appointment a
				    JOIN doctor d ON a.doctor_id = d.id
				    JOIN user u ON d.user_id = u.id
				    WHERE a.patient_id = ?
				    ORDER BY a.id DESC
				""";

		try (Connection con = dbUtil.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setInt(1, patientId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				AppointmentDTO dto = new AppointmentDTO();

				dto.setId(rs.getInt("id"));
				dto.setAppointment_date(rs.getString("appointment_date"));
				dto.setAppointment_time(rs.getString("appointment_time"));
				dto.setStatus(rs.getString("status"));
				dto.setReason(rs.getString("reason"));

				dto.setDoctorName(rs.getString("first_name"));
				dto.setSpecialization(rs.getString("specialization"));

				list.add(dto);
			}
		}

		return list;
	}
}