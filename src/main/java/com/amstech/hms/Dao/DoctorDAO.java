package com.amstech.hms.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.hms.model.DoctorDTO;
import com.amstech.hms.util.DButil;

public class DoctorDAO {

    private DButil dbUtil;

    public DoctorDAO(DButil dbUtil) {
        this.dbUtil = dbUtil;
    }

	/*
	 * public List<DoctorDTO> getAllDoctorsFull() throws Exception {
	 * 
	 * List<DoctorDTO> doctorList = new ArrayList<>();
	 * 
	 * 
	 * String sql = """ SELECT d.id, d.status, u.first_name, u.last_name, u.email,
	 * u.mobile_no, d.specialization, d.shift_time, d.user_id FROM doctor d JOIN
	 * user u ON d.user_id = u.id WHERE d.status = 'ACTIVE' """;
	 * 
	 * 
	 * System.out.println("[DoctorDAO] getAllDoctorsFull called");
	 * 
	 * 
	 * try (Connection con = dbUtil.getConnection(); PreparedStatement ps =
	 * con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
	 * 
	 * while (rs.next()) {
	 * 
	 * DoctorDTO d = new DoctorDTO();
	 * 
	 * d.setId(rs.getInt("id"));
	 * d.setSpecialization(rs.getString("specialization"));
	 * d.setShift_time(rs.getString("shift_time"));
	 * d.setFirst_name(rs.getString("first_name"));
	 * d.setLast_name(rs.getString("last_name")); d.setEmail(rs.getString("email"));
	 * d.setMobile_no(rs.getString("mobile_no"));
	 * d.setUser_id(rs.getInt("user_id")); d.setStatus(rs.getString("status"));
	 * 
	 * doctorList.add(d);
	 * 
	 * } }
	 * 
	 * return doctorList; }
	 */
    
    
    public List<DoctorDTO> getAllDoctorsFull(String search) throws Exception {

        List<DoctorDTO> doctorList = new ArrayList<>();

        String sql = """
            SELECT d.id, d.status, u.first_name, u.last_name, u.email,
                   u.mobile_no, d.specialization, d.shift_time, d.user_id
            FROM doctor d
            JOIN user u ON d.user_id = u.id
            WHERE d.status = 'ACTIVE'
        """;

        // 🔥 SEARCH ADD
        if (search != null && !search.trim().isEmpty()) {
            sql += " AND (u.first_name LIKE ? OR d.specialization LIKE ?)";
        }

        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            // 🔥 SET PARAMS
            if (search != null && !search.trim().isEmpty()) {
                ps.setString(1, "%" + search + "%");
                ps.setString(2, "%" + search + "%");
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                DoctorDTO d = new DoctorDTO();

                d.setId(rs.getInt("id"));
                d.setSpecialization(rs.getString("specialization"));
                d.setShift_time(rs.getString("shift_time"));
                d.setFirst_name(rs.getString("first_name"));
                d.setLast_name(rs.getString("last_name"));
                d.setEmail(rs.getString("email"));
                d.setMobile_no(rs.getString("mobile_no"));
                d.setUser_id(rs.getInt("user_id"));
                d.setStatus(rs.getString("status"));

                doctorList.add(d);
            }
        }

        return doctorList;
    }
    public DoctorDTO getDoctorByUserId(int userId) throws Exception {

        DoctorDTO d = null;

        String sql = """
            SELECT d.*, u.first_name, u.email
            FROM doctor d
            JOIN user u ON d.user_id = u.id
            WHERE d.user_id = ?
        """;
        System.out.println("FILTER METHOD RUNNING");
        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = new DoctorDTO();

                d.setId(rs.getInt("id"));
                d.setSpecialization(rs.getString("specialization"));
                d.setShift_time(rs.getString("shift_time"));
                d.setStatus(rs.getString("status"));
                d.setFirst_name(rs.getString("first_name"));
                d.setEmail(rs.getString("email"));
            }
        }

        return d;
    }
    public List<DoctorDTO> getPendingDoctors() throws Exception {

        List<DoctorDTO> list = new ArrayList<>();

        String sql = """
            SELECT d.*, u.first_name, u.email
            FROM doctor d
            JOIN user u ON d.user_id = u.id
            WHERE d.status = 'PENDING'
        """;

        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DoctorDTO d = new DoctorDTO();

                d.setId(rs.getInt("id"));
                d.setFirst_name(rs.getString("first_name"));
                d.setEmail(rs.getString("email"));
                d.setSpecialization(rs.getString("specialization"));
                d.setStatus(rs.getString("status"));
                
                
                list.add(d);
            }
        }

        return list;
    }
    public void updateDoctorStatusByUserId(int userId, String status) throws Exception {

        if (!"ACTIVE".equalsIgnoreCase(status) &&
            !"PENDING".equalsIgnoreCase(status) &&
            !"REJECTED".equalsIgnoreCase(status)) {

            throw new Exception("Invalid status: " + status);
        }

        String sql = "UPDATE doctor SET status=? WHERE id=?";

        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status.toUpperCase());
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            System.out.println("🔥 Rows updated = " + rows);
        }
    }
    public DoctorDTO getDoctorById(int id) throws Exception {

        DoctorDTO d = null;

        String sql = "SELECT * FROM doctor WHERE id = ?";

        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                d = new DoctorDTO();

                d.setId(rs.getInt("id"));
                d.setStatus(rs.getString("status"));
            }
        }

        return d;
    }
    public void updateAppointmentStatus(int id, String status) throws Exception {

        String sql = "UPDATE appointment SET status=? WHERE id=?";

        try (Connection con = dbUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, id);

            ps.executeUpdate();
        }
    }
    


public List<DoctorDTO> getFilteredDoctors(String search, String specialization, String date) throws Exception {

    List<DoctorDTO> list = new ArrayList<>();

    StringBuilder sql = new StringBuilder("""
        SELECT d.id, d.status, u.first_name, u.last_name, u.email,
               u.mobile_no, d.specialization, d.shift_time, d.user_id
        FROM doctor d
        JOIN user u ON d.user_id = u.id
        WHERE d.status = 'ACTIVE'
    """);

    List<Object> params = new ArrayList<>();

    // 🔍 SEARCH
    if (!search.isEmpty()) {
        sql.append(" AND u.first_name LIKE ?");
        params.add("%" + search + "%");
    }

    // 🏥 SPECIALIZATION
    if (!specialization.isEmpty()) {
        sql.append(" AND d.specialization = ?");
        params.add(specialization);
    }

    // 📅 DATE (availability check)
    if (!date.isEmpty()) {
        sql.append("""
            AND d.id NOT IN (
                SELECT doctor_id FROM appointment
                WHERE appointment_date = ?
            )
        """);
        params.add(date);
    }

    Connection con = dbUtil.getConnection();
    PreparedStatement ps = con.prepareStatement(sql.toString());

    for (int i = 0; i < params.size(); i++) {
        ps.setObject(i + 1, params.get(i));
    }

    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        DoctorDTO d = new DoctorDTO();

        d.setId(rs.getInt("id"));
        d.setSpecialization(rs.getString("specialization"));
        d.setShift_time(rs.getString("shift_time"));
        d.setFirst_name(rs.getString("first_name"));
        d.setLast_name(rs.getString("last_name"));
        d.setEmail(rs.getString("email"));
        d.setMobile_no(rs.getString("mobile_no"));
        d.setUser_id(rs.getInt("user_id"));
        d.setStatus(rs.getString("status"));

        list.add(d);
    }

    return list;
}
public List<String> getAllSpecializations() throws Exception {

    List<String> list = new ArrayList<>();

    String sql = "SELECT DISTINCT specialization FROM doctor";

    PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
    ResultSet rs = ps.executeQuery();

    while (rs.next()) {
        list.add(rs.getString("specialization"));
    }

    return list;
}



public int getTotalDoctors() {
    int count = 0;
    try {
        Connection con = dbUtil.getConnection();
        String sql = "SELECT COUNT(*) FROM doctor";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        System.out.println("Doctors count = " + count);
    } catch(Exception e) {
        e.printStackTrace();
    }
    return count;
}


public int getTotalPatients() {
    int count = 0;
    try {
        String sql = "SELECT COUNT(*) FROM patient";
        PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch(Exception e) {
        e.printStackTrace();
    }
    return count;
}
}


//SELECT 
//u.first_name,
//u.last_name,
//d.id,
//d.specilaization,
//d.shift_time
//FROM doctor d
//JOIN user u ON d.user_id = u.id