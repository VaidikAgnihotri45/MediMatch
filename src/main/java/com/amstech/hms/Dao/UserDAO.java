package com.amstech.hms.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;

import com.amstech.hms.model.UserDTO;
import com.amstech.hms.util.DButil;

public class UserDAO {

	private DButil dbUtil;

	public UserDAO(DButil dbUtil) {
		System.out.println("UserDAO : Object is created");
		this.dbUtil = dbUtil;

	}

	private final String USER_SAVE = "insert into user(first_name,last_name,email,mobile_no,age,gender,address,hospital_id,role_id,password) "
			+ " values (?,?,?,?,?,?,?,?,?,?)";
//	select * from user where (email = ? or mobile_no = ?) and password = ?
	private final String USER_FIND_BY_USERNAME_PASSWORD = "select * from user where (email = ? or mobile_no = ?) and password = ?";
	private final String UPDATE_USER = "update user set first_name = ?,last_name =?, email = ?, mobile_no = ?, gender = ?,age = ?,address = ? where id = ?";

//	private final String UPDATE_USER = "update user set first_name = ?,last_name =?, email = ?, mobile_no = ?,gender = ?,password = ?, age = ?,address = ? where id = ?";
	private final String USER_FIND_BY_ID = "select * from user where id=?";
	
	private final String FIND_BY_MobileNo = "select * from user where mobile_no = ?";
	private final String FIND_USER = "select * from user where mobile_no = ? or firstname = ? or lastname = ? or email = ?  ";
//	private final String Login_User = "select * from user where (email = ? or mobile_no = ? or first_name = ? or last_name = ? ) and password = ?";
	private final String Login_User = "SELECT * FROM user WHERE email=? OR mobile_no=?";
	private final String USER_DELETE_BY_ID = "delete from user where id=?";
//
	/*
	 * public int save(UserDTO userDTO) throws ClassNotFoundException, SQLException,
	 * ClassNotFoundException {
	 * 
	 * Connection connection = null; PreparedStatement pstmt = null;
	 * 
	 * try { // connection
	 * 
	 * connection = dbUtil.getConnection();
	 * 
	 * // Query pstmt = connection.prepareStatement(USER_SAVE);
	 * 
	 * pstmt.setString(1, userDTO.getFirst_name()); pstmt.setString(2,
	 * userDTO.getLast_name()); pstmt.setString(3, userDTO.getEmail());
	 * pstmt.setString(4, userDTO.getMobile_no()); pstmt.setInt(5,
	 * userDTO.getAge()); pstmt.setString(6, userDTO.getGender());
	 * pstmt.setString(7, userDTO.getAddress()); pstmt.setInt(8,
	 * userDTO.getHospital_id()); pstmt.setInt(9, userDTO.getRole_id());
	 * pstmt.setString(10, userDTO.getPassword());
	 * 
	 * return pstmt.executeUpdate(); } catch (Exception e) { throw e; } finally {
	 * dbUtil.getClose(connection, pstmt); } }
	 */
	//
	
	
	public int save(UserDTO userDTO) throws ClassNotFoundException, SQLException {

	    Connection connection = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;

	    try {

	        connection = dbUtil.getConnection();

	        // 🔥 Important: generated keys enable
	        pstmt = connection.prepareStatement(USER_SAVE, PreparedStatement.RETURN_GENERATED_KEYS);

	        pstmt.setString(1, userDTO.getFirst_name());
	        pstmt.setString(2, userDTO.getLast_name());
	        pstmt.setString(3, userDTO.getEmail());
	        pstmt.setString(4, userDTO.getMobile_no());
	        pstmt.setInt(5, userDTO.getAge());
	        pstmt.setString(6, userDTO.getGender());
	        pstmt.setString(7, userDTO.getAddress());
	        pstmt.setInt(8, userDTO.getHospital_id());
	        pstmt.setInt(9, userDTO.getRole_id());
	        pstmt.setString(10, userDTO.getPassword());

	        int count = pstmt.executeUpdate();

	        if (count > 0) {

	            // 🔥 Get generated user ID
	            rs = pstmt.getGeneratedKeys();

	            if (rs.next()) {

	                int userId = rs.getInt(1);
	                System.out.println("Generated User ID = " + userId);

	                // 🔵 AUTO CREATE PATIENT
	                if (userDTO.getRole_id() == 1) {

	                    String sql = "INSERT INTO patient (user_id, status) VALUES (?, 'Active')";
	                    PreparedStatement ps = connection.prepareStatement(sql);
	                    ps.setInt(1, userId);
	                    ps.executeUpdate();

	                    System.out.println("Patient created automatically");
	                }

	                // 🔵 AUTO CREATE DOCTOR
	                else if (userDTO.getRole_id() == 2) {

	                    String sql = "INSERT INTO doctor (user_id, status) VALUES (?, 'PENDING')";
	                    PreparedStatement ps = connection.prepareStatement(sql);
	                    ps.setInt(1, userId);
	                    ps.executeUpdate();

	                    System.out.println("Doctor created automatically");
	                }
	            }
	        }

	        return count;

	    } catch (Exception e) {
	        throw e;
	    } finally {
	        dbUtil.getClose(connection, pstmt, rs);
	    }
	}

//		===============================================================================================================================================
	/*
	 * public UserDTO UserLogin(String username, String password) throws Exception {
	 * Connection connection = null; PreparedStatement pstmt = null; ResultSet rs =
	 * null; UserDTO userDTO = null; try { connection = dbUtil.getConnection();
	 * pstmt = connection.prepareStatement(Login_User); pstmt.setString(1,
	 * username); pstmt.setString(2, username); pstmt.setString(3, username);
	 * pstmt.setString(4, username); pstmt.setString(5, password);
	 * 
	 * rs = pstmt.executeQuery();
	 * 
	 * if (rs.next()) { userDTO = new UserDTO(); userDTO.setId(rs.getInt("id"));
	 * userDTO.setMobile_no(rs.getString("mobile_no"));
	 * userDTO.setFirst_name(rs.getString("first_name"));
	 * userDTO.setLast_name(rs.getString("last_name"));
	 * userDTO.setPassword(rs.getString("password"));
	 * userDTO.setEmail(rs.getString("email"));
	 * 
	 * userDTO.setRole_id(rs.getInt("role_id"));
	 * System.out.println("[UserDAO] Role ID fetched from DB = " +
	 * userDTO.getRole_id());
	 * 
	 * } return userDTO; } catch (Exception e) { throw e; } finally {
	 * dbUtil.getClose(connection, pstmt, rs); } }
	 */
	
	
//	===================================================
	
	
	
	
	public UserDTO UserLogin(String username) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO userDTO = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(Login_User);
			pstmt.setString(1, username);
			pstmt.setString(2, username);

			rs = pstmt.executeQuery();	

			if (rs.next()) {
				userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setMobile_no(rs.getString("mobile_no"));
				userDTO.setFirst_name(rs.getString("first_name"));
				userDTO.setLast_name(rs.getString("last_name"));
				userDTO.setPassword(rs.getString("password"));
				userDTO.setEmail(rs.getString("email"));
				
				userDTO.setRole_id(rs.getInt("role_id"));
			    System.out.println("[UserDAO] Role ID fetched from DB = " + userDTO.getRole_id());

			}
			return userDTO;
		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.getClose(connection, pstmt, rs);
		}
	}
		
	
	
	

	// =============================================================================================================================================
	public UserDTO findByUserNamePassword(String username, String password) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO userDTO = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_FIND_BY_USERNAME_PASSWORD);
			pstmt.setString(1, username);
			pstmt.setString(2, username);
			pstmt.setString(3, password);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setMobile_no(rs.getString("mobile_no"));
				userDTO.setFirst_name(rs.getString("first_name"));
				userDTO.setLast_name(rs.getString("last_name"));
				userDTO.setPassword(rs.getString("password"));
				userDTO.setEmail(rs.getString("email"));
			}
			return userDTO;
		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.getClose(connection, pstmt, rs);
		}
	}

	// ==============================================================================================================================================================
	public UserDTO FindUser(int id) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO userDTO = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_FIND_BY_ID);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setFirst_name(rs.getString("first_name"));
				userDTO.setLast_name(rs.getString("last_name"));
				userDTO.setEmail(rs.getString("email"));
				userDTO.setMobile_no(rs.getString("mobile_no"));
				userDTO.setAddress(rs.getString("Address"));
				userDTO.setAge(rs.getInt("age"));
				userDTO.setGender(rs.getString("gender"));

//					String date = new SimpleDateFormat("yyyy-MM-dd").format(rs.getDate("dob"));
//					userDTO.setDob(date);
//				userDTO.setGender("gender");

			}
			return userDTO;
		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.getClose(connection, pstmt, rs);
		}
	}

	public int updateById(UserDTO userDTO) throws Exception, NumberFormatException ,NullPointerException,SQLIntegrityConstraintViolationException,SQLException
	{
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			// connection
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(UPDATE_USER);
//				
			pstmt.setString(1, userDTO.getFirst_name());
			pstmt.setString(2, userDTO.getLast_name());
			pstmt.setString(3, userDTO.getEmail());
			pstmt.setString(4, userDTO.getMobile_no());
			pstmt.setString(5, userDTO.getGender());
//			pstmt.setString(6, userDTO.getPassword());
			pstmt.setInt(6, userDTO.getAge());

			pstmt.setString(7, userDTO.getAddress());
			pstmt.setInt(8, userDTO.getId());

			int count = pstmt.executeUpdate();
			return count;

		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.getClose(connection, pstmt);
		}

	}

	public UserDTO findByMobileNo(String mobile_no) throws Exception, NumberFormatException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserDTO userDTO = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(FIND_BY_MobileNo);
			pstmt.setString(1, mobile_no);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				userDTO = new UserDTO();
				userDTO.setId(rs.getInt("id"));
				userDTO.setFirst_name(rs.getString("first_name"));
				userDTO.setMobile_no(rs.getString("mobile_no"));
				userDTO.setEmail(rs.getString("email"));
				userDTO.setAge(rs.getInt("age"));
			}
			return userDTO;
		} catch (Exception e) {
			throw e;
		} finally {
			dbUtil.getClose(connection, pstmt, rs);
		}

	}

	public int deleteById(int id) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = dbUtil.getConnection();
			pstmt = connection.prepareStatement(USER_DELETE_BY_ID);
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();

		} catch (Exception e) {
			throw e;
		} finally {
		}
	}
	public boolean isEmailExists(String email) throws Exception {
	    String sql = "SELECT id FROM user WHERE email = ?";
	    PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
	    ps.setString(1, email);
	    ResultSet rs = ps.executeQuery();
	    return rs.next();
	}
	public boolean isMobileExists(String mobile) throws Exception {
	    String sql = "SELECT id FROM user WHERE mobile_no = ?";
	    PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
	    ps.setString(1, mobile);
	    ResultSet rs = ps.executeQuery();
	    return rs.next();
	}


	public UserDTO findByEmail(String email) throws Exception {

	    String sql = "SELECT * FROM user WHERE email = ?";
	    PreparedStatement ps = dbUtil.getConnection().prepareStatement(sql);
	    ps.setString(1, email);

	    ResultSet rs = ps.executeQuery();

	    if(rs.next()){
	        UserDTO user = new UserDTO();
	        user.setId(rs.getInt("id"));
	        user.setEmail(rs.getString("email"));
	        return user;
	    }
	    return null;
	}
	public void updatePassword(int userId, String newHash) throws Exception {

	    Connection con = dbUtil.getConnection();

	    String sql = "UPDATE user SET password=? WHERE id=?";
	    PreparedStatement ps = con.prepareStatement(sql);

	    ps.setString(1, newHash);
	    ps.setInt(2, userId);

	    ps.executeUpdate();

	    con.close();
	}
	
	public void saveOTP(int userId, String otp, Timestamp expiry) throws Exception {

	    Connection con = dbUtil.getConnection();

	    String sql = "UPDATE user SET otp=?, otp_expiry=? WHERE id=?";
	    PreparedStatement ps = con.prepareStatement(sql);

	    ps.setString(1, otp);
	    ps.setTimestamp(2, expiry);
	    ps.setInt(3, userId);

	    ps.executeUpdate();

	    con.close();
	}
	
	public boolean verifyOTP(int userId, String otp) throws Exception {

	    Connection con = dbUtil.getConnection();

	    String sql = "SELECT otp, otp_expiry FROM user WHERE id=?";
	    PreparedStatement ps = con.prepareStatement(sql);

	    ps.setInt(1, userId);

	    ResultSet rs = ps.executeQuery();

	    if (rs.next()) {

	        String dbOtp = rs.getString("otp");
	        Timestamp expiry = rs.getTimestamp("otp_expiry");

	        // 🔥 CASE 1: OTP match
	        if (dbOtp != null && dbOtp.equals(otp)) {

	            // 🔥 CASE 2: expiry check
	            if (expiry != null && expiry.after(new Timestamp(System.currentTimeMillis()))) {

	                con.close();
	                return true;

	            } else {
	                System.out.println("OTP expired");
	            }

	        } else {
	            System.out.println("OTP mismatch");
	        }
	    }

	    con.close();
	    return false;
	}
}
