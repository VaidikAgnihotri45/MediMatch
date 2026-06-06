package com.amstech.hms.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.hms.model.PatientDTO;
import com.amstech.hms.util.DButil;

public class PatientDAO {

	private DButil dbUtil;

	public PatientDAO(DButil dbUtil) {
		this.dbUtil = dbUtil;
	}

	public int getPatientIdByUserId(int userId) throws Exception {

		int patientId = 0;

		String sql = "SELECT id FROM patient WHERE user_id=?";

		PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
		ps.setInt(1, userId);

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			patientId = rs.getInt("id");
		}

		return patientId;
	}

	public List<PatientDTO> getAllPatientsWithUserData() throws Exception {

		List<PatientDTO> list = new ArrayList<>();

		String sql = """
				    SELECT
				        u.id AS user_id,
				        u.first_name,
				        u.email,
				        u.mobile_no,
				        p.status
				    FROM user u
				    JOIN patient p ON u.id = p.user_id
				    WHERE u.role_id = 1
				    ORDER BY u.id DESC
				""";

		try (PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql); ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {

				PatientDTO dto = new PatientDTO();

				dto.setId(rs.getInt("user_id"));
				dto.setFirst_name(rs.getString("first_name"));
				dto.setEmail(rs.getString("email"));
				dto.setMobile_no(rs.getString("mobile_no"));
				dto.setStatus(rs.getString("status"));

				list.add(dto);
			}
		}

		return list;
	}

	public void updateStatus(int userId, String status) throws Exception {

		String sql = "UPDATE patient SET status=? WHERE user_id=?";

		PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
		ps.setString(1, status);
		ps.setInt(2, userId);

		int rows = ps.executeUpdate();

		System.out.println("Updated rows = " + rows);
	}

	public void deletePatient(int userId) throws Exception {

	    // delete appointment first (agar hai)
	    String sql0 = "DELETE FROM appointment WHERE patient_id = (SELECT id FROM patient WHERE user_id=?)";
	    PreparedStatement ps0 = dbUtil.getConnection().prepareStatement(sql0);
	    ps0.setInt(1, userId);
	    ps0.executeUpdate();

	    // patient delete
	    String sql1 = "DELETE FROM patient WHERE user_id=?";
	    PreparedStatement ps1 = dbUtil.getConnection().prepareStatement(sql1);
	    ps1.setInt(1, userId);
	    ps1.executeUpdate();

	    // user delete
	    String sql2 = "DELETE FROM user WHERE id=?";
	    PreparedStatement ps2 = dbUtil.getConnection().prepareStatement(sql2);
	    ps2.setInt(1, userId);
	    ps2.executeUpdate();

	    System.out.println("Deleted safely userId = " + userId);
	}
}
