package com.amstech.hms.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import com.amstech.hms.Dao.UserDAO;
import com.amstech.hms.model.UserDTO;



public class UserService {
	UserDAO userDAO;

	public UserService(UserDAO userDAO) {
		System.out.println("User Service : Object is created");
		this.userDAO = userDAO;
	}

	public int save(UserDTO userDTO) throws ClassNotFoundException, SQLException {
		int count = userDAO.save(userDTO);
		return count;
	
	}

	public UserDTO findByUsernamePassword(String username, String password) throws Exception {
		return userDAO.findByUserNamePassword(username, password);
	}

	/*
	 * public UserDTO UserLogin(String username, String password) throws Exception {
	 * return userDAO.UserLogin(username, password); }
	 */
	
	
	public UserDTO UserLogin(String username) throws Exception {
	    return userDAO.UserLogin(username);
	}
	
	public int UpdateUser(UserDTO userDTO) throws Exception {
		return userDAO.updateById(userDTO);

	}

	public UserDTO FindUser(int id) throws Exception {
		return userDAO.FindUser(id);
	}
	public UserDTO findByMobileNo(String mobile_no) throws Exception {
		return userDAO.findByMobileNo(mobile_no);
	}
		public int deleteById(int id) throws Exception {
			System.err.println("mathod delete service");
			return userDAO.deleteById(id);
		}
		public UserDTO FindByEmail(String email) throws Exception {
		    return userDAO.findByEmail(email);
		}
		
		public void updatePassword(int userId, String newHash) throws Exception {
		    userDAO.updatePassword(userId, newHash);
		}
		
		
		public void saveOTP(int userId, String otp, Timestamp expiry) {
		    try {
		        userDAO.saveOTP(userId, otp, expiry);
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
		
//		public void saveOTP(int userId, String otp, Timestamp expiry)
//		public boolean verifyOTP(int userId, String otp)
//}	
		public boolean verifyOTP(int userId, String otp) {
		    try {
		        return userDAO.verifyOTP(userId, otp);
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}
}

