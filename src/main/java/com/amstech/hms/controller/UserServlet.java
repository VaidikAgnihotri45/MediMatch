package com.amstech.hms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.mindrot.jbcrypt.BCrypt;
/*import org.mindrot.jbcrypt.BCrypt;
*/import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.amstech.hms.Dao.AppointmentDAO;
import com.amstech.hms.Dao.PrescriptionDAO;
import com.amstech.hms.Dao.UserDAO;
import com.amstech.hms.model.AppointmentDTO;
import com.amstech.hms.model.PrescriptionDTO;
import com.amstech.hms.model.UserDTO;
import com.amstech.hms.service.UserService;
import com.amstech.hms.util.DButil;
import com.amstech.hms.util.EmailUtil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UserServlet")

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DButil dbUtil;
	private UserDAO userDAO;
	private UserService userService;

	public UserServlet() {
		System.out.println("User UserServlet : Object is Created");
		this.dbUtil = new DButil();
		this.userDAO = new UserDAO(dbUtil);
		this.userService = new UserService(userDAO);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("Method:get 541551524");

		String task = request.getParameter("task");
		System.err.println("task: " + task);

		if (task == null) {
			System.out.println("Task cannot be null.");
		} else if (task.equalsIgnoreCase("findById")) {
			FindUser(request, response);
		} else if (task.equalsIgnoreCase("findByMobileNo")) {
			FindByMoblieNo(request, response);
		} else if (task.equalsIgnoreCase("deleteById")) {
			deleteById(request, response);
		} else if (task.equalsIgnoreCase("logout")) {

			request.getSession().invalidate(); // session destroy
			response.sendRedirect("auth/Login.jsp");

		} else if (task.equalsIgnoreCase("myAppointments")) {
			showMyAppointments(request, response);
		} else if (task.equalsIgnoreCase("prescriptionHistory")) {
			showPrescriptionHistory(request, response);
		}

		else {
			System.err.println("No Operation found");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {
		System.out.println("Method:post 456454554.");

		String task = request.getParameter("task");
		System.err.println("task: " + task);

		if (task == null) {
			System.err.println("Task cannot be null.");
			// throw excep
		} else if (task.equalsIgnoreCase("signup")) {
			save(request, response);
		} else if (task.equalsIgnoreCase("UsernamePassword")) {
			findByUsernamePassword(request, response);
		} else if (task.equalsIgnoreCase("updateById")) {
			UpdateUser(request, response);

		} else if (task.equalsIgnoreCase("Login")) {
			UserLogin(request, response);

		}
		if (task.equalsIgnoreCase("forgotPassword")) {
			forgotPassword(request, response);
		} else if (task.equalsIgnoreCase("verifyOTP")) {
			verifyOTP(request, response);
		} else if (task.equalsIgnoreCase("resetPassword")) {
			resetPassword(request, response);
		} else {
			System.err.println("Method is not found");
			// throw excep
		}

	}

	private void save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 🔥 STEP 1: CLEAN INPUT
		String email = request.getParameter("email") != null ? request.getParameter("email").toLowerCase().trim() : "";

		String mobile = request.getParameter("mobile_no") != null ? request.getParameter("mobile_no").trim() : "";

		// 🔥 STEP 2: EMAIL VALIDATION
		if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[A-Za-z]{2,}$")) {

			request.setAttribute("Message", "Invalid email format!");
			request.getRequestDispatcher("auth/Signup.jsp").forward(request, response);
			return;
		}

		// 🔥 STEP 3: MOBILE VALIDATION
		if (!mobile.matches("^[0-9]{10}$")) {

			request.setAttribute("Message", "Invalid mobile number (10 digits required)!");
			request.getRequestDispatcher("auth/Signup.jsp").forward(request, response);
			return;
		}

		try {

			// 🔥 STEP 4: DUPLICATE CHECK (APPLICATION LEVEL)
			if (userDAO.isEmailExists(email)) {

				request.setAttribute("Message", "Email already exists!");
				request.getRequestDispatcher("auth/Signup.jsp").forward(request, response);
				return;
			}

			if (userDAO.isMobileExists(mobile)) {

				request.setAttribute("Message", "Mobile number already exists!");
				request.getRequestDispatcher("auth/Signup.jsp").forward(request, response);
				return;
			}

			String ageStr = request.getParameter("age");

			// invalid input check
			if (ageStr == null || !ageStr.matches("\\d+")) {

				request.setAttribute("Message", "Age must be a valid number!");
				request.getRequestDispatcher("auth/Signup.jsp").forward(request, response);
				return;
			}

			int age = Integer.parseInt(ageStr);

			// range check
			if (age < 1 || age > 120) {

				request.setAttribute("Message", "Age must be between 1 and 120");
				request.getRequestDispatcher("auth/Signup.jsp").forward(request, response);
				return;
			}

			// set value

			// SET DTO
			UserDTO userDTO = new UserDTO();

			userDTO.setFirst_name(request.getParameter("first_name"));
			userDTO.setLast_name(request.getParameter("last_name"));
			userDTO.setEmail(email);
			userDTO.setMobile_no(mobile);
			userDTO.setAddress(request.getParameter("address"));
			userDTO.setAge(age);

//	        userDTO.setAge(Integer.parseInt(request.getParameter("age")));
			userDTO.setGender(request.getParameter("gender"));
			userDTO.setHospital_id(1);
			userDTO.setRole_id(Integer.parseInt(request.getParameter("role_id")));

			String plainPassword = request.getParameter("password");
			String hashedPassword = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
			System.out.println(BCrypt.hashpw("test123", BCrypt.gensalt()));//
			userDTO.setPassword(hashedPassword);

			// userDTO.setPassword(request.getParameter("password"));

			// SAVE TO DB
			int count = userService.save(userDTO);

			if (count > 0) {

				System.out.println("✅ User created: " + userDTO.getFirst_name());

				request.setAttribute("status", "Success");
				request.setAttribute("Message", "Account created successfully!");
				// request.setAttribute("redirectUrl", "Login.jsp");
				request.setAttribute("redirectUrl", request.getContextPath() + "/auth/Login.jsp");

				RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");
				rd.forward(request, response);

			} else {

				request.setAttribute("status", "Error");
				request.setAttribute("Message", "Failed to create account");
				request.setAttribute("redirectUrl", request.getContextPath() + "/auth/Signup.jsp");

				RequestDispatcher rd = request.getRequestDispatcher("/public/Messages.jsp");
				rd.forward(request, response);
			}

		} catch (SQLIntegrityConstraintViolationException e) {

			// DB LEVEL DUPLICATE ERROR
			request.setAttribute("Message", "Email or Mobile already exists!");
			request.getRequestDispatcher("auth/Signup.jsp").forward(request, response);

		} catch (Exception e) {

			e.printStackTrace();

			request.setAttribute("status", "Error");
			request.setAttribute("Message", "Error: " + e.getMessage());
			// request.setAttribute("redirectUrl", "Signup.jsp");
			request.setAttribute("redirectUrl", request.getContextPath() + "/auth/Signup.jsp");

			RequestDispatcher rd = request.getRequestDispatcher("/public/Messages.jsp");
			rd.forward(request, response);
		}
	}

	/*
	 * private void save(HttpServletRequest request, HttpServletResponse response)
	 * throws ServletException, IOException { String email =
	 * request.getParameter("email").toLowerCase().trim(); String mobile =
	 * request.getParameter("mobile_no").trim(); if(email == null ||
	 * !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
	 * 
	 * request.setAttribute("Message", "Invalid email!");
	 * request.getRequestDispatcher("auth/Signup.jsp") .forward(request, response);
	 * return; } UserDTO userDTO = new UserDTO();
	 * 
	 * userDTO.setFirst_name(request.getParameter("first_name"));
	 * userDTO.setLast_name(request.getParameter("last_name"));
	 * userDTO.setEmail(email); userDTO.setMobile_no(mobile);
	 * userDTO.setAddress(request.getParameter("address"));
	 * userDTO.setAge(Integer.parseInt(request.getParameter("age")));
	 * userDTO.setGender(request.getParameter("gender")); userDTO.setHospital_id(1);
	 * userDTO.setRole_id(Integer.parseInt(request.getParameter("role_id")));
	 * userDTO.setPassword(request.getParameter("password")); try { int count =
	 * userService.save(userDTO);
	 * 
	 * if (count > 0) {
	 * 
	 * System.out.println(userDTO.getFirst_name());
	 * 
	 * // 🔥 ADD THIS BLOCK if (userDTO.getRole_id() == 2) {
	 * 
	 * try { DButil db = new DButil(); Connection con = db.getConnection();
	 * 
	 * // 👉 user ka id nikal String getIdSql = "SELECT id FROM user WHERE email=?";
	 * PreparedStatement ps1 = con.prepareStatement(getIdSql); ps1.setString(1,
	 * userDTO.getEmail());
	 * 
	 * 
	 * ResultSet rs = ps1.executeQuery(); int userId = rs.getInt("id");
	 * 
	 * 
	 * ResultSet rs = ps1.executeQuery(); if (rs.next()) { int userId =
	 * rs.getInt("id"); }
	 * 
	 * 
	 * 
	 * 
	 * if (rs.next()) {
	 * 
	 * int userId = rs.getInt("id");
	 * 
	 * // 👉 doctor table insert String sql =
	 * "INSERT INTO doctor (user_id, status) VALUES (?, 'PENDING')";
	 * PreparedStatement ps2 = con.prepareStatement(sql);
	 * 
	 * ps2.setInt(1, userId); ps2.executeUpdate();
	 * 
	 * System.out.println("Doctor record created (PENDING)"); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * if (!rs.next()) { // ❌ row nahi hai → insert kar String insertSql =
	 * "INSERT INTO doctor (user_id, status) VALUES (?, 'PENDING')";
	 * PreparedStatement insertPs = con.prepareStatement(insertSql);
	 * insertPs.setInt(1, userId); insertPs.executeUpdate();
	 * 
	 * System.out.println("Doctor row created"); }
	 * 
	 * 
	 * 
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * // 🔥 existing code RequestDispatcher rd =
	 * request.getRequestDispatcher("admin/pati.jsp"); rd.forward(request,
	 * response);
	 * 
	 * System.out.println("User account created Successfully");
	 * 
	 * 
	 * } else { System.out.println("Failed to create user Account");
	 * System.out.println("Failed to Login."); request.setAttribute("status",
	 * "Failed"); request.setAttribute("Message",
	 * "Failed to login Incorrect username and password ");
	 * request.setAttribute("redirectUrl", "Signup.jsp"); RequestDispatcher rd =
	 * request.getRequestDispatcher("Message.jsp"); rd.forward(request, response);
	 * 
	 * } } catch (ClassNotFoundException e) { e.printStackTrace(); } catch
	 * (SQLException e) { e.printStackTrace(); } }
	 */

	private void findByUsernamePassword(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("username: " + username);
		System.out.println("password: " + password);
		try {
			UserDTO activeUserDTO = userService.findByUsernamePassword(username, password);
			if (activeUserDTO != null) {
				System.out.println("User login successfully.");
				System.out.println(" FirstName: " + activeUserDTO.getFirst_name());
				System.out.println("LastName:" + activeUserDTO.getLast_name());
				System.out.println("Email: " + activeUserDTO.getEmail());
				request.setAttribute("activeUserDTO", activeUserDTO);
				RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
				rd.forward(request, response);
			} else {
				System.out.println("Failed to Login.");
				request.setAttribute("status", "Failed");
				request.setAttribute("Message", "Failed to login Incorrect username and password ");

				request.setAttribute("redirectUrl", "Login.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("Login.jsp");
			// redirect with message
		}
	}

//====================================================================================================================================
	/*
	 * private void UserLogin(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException { String username =
	 * request.getParameter("username"); String password =
	 * request.getParameter("password"); System.out.println("username: " +
	 * username); System.out.println("password: " + password); try { UserDTO
	 * activeUserDTO = userService.UserLogin(username, password); if (activeUserDTO
	 * != null) {
	 * 
	 * System.out.println("User login successfully..");
	 * System.out.println("FirstName: " + activeUserDTO.getFirst_name());
	 * System.out.println("LastName:" + activeUserDTO.getLast_name());
	 * System.out.println("Email: " + activeUserDTO.getEmail()); //
	 * request.setAttribute("activeUserDTO", activeUserDTO);
	 * request.getSession().setAttribute("activeUserDTO", activeUserDTO);
	 * RequestDispatcher rd = request.getRequestDispatcher("/admin/pati.jsp");
	 * rd.forward(request, response);
	 * 
	 * } else { System.out.println("Failed to Login..");
	 * request.setAttribute("status", "Failed"); request.setAttribute("Message",
	 * "Failed to login Incorrect username and password ");
	 * request.setAttribute("redirectUrl", "Login.jsp"); RequestDispatcher rd =
	 * request.getRequestDispatcher("Message.jsp"); rd.forward(request, response); }
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * response.sendRedirect("Login.jsp.jsp"); // redirect with message } }
	 */

	//////////////////////////////////////////////////////////////////////////////////////

//	2222222/////////////

	/*
	 * private void UserLogin(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException {
	 * 
	 * String username = request.getParameter("username"); String password =
	 * request.getParameter("password");
	 * 
	 * System.out.println("[UserServlet] Login attempt");
	 * System.out.println("Username: " + username);
	 * 
	 * try {
	 * 
	 * UserDTO activeUserDTO = userService.UserLogin(username, password);
	 * 
	 * if (activeUserDTO != null) {
	 * 
	 * System.out.println("[UserServlet] Login successful");
	 * 
	 * request.getSession().setAttribute("activeUserDTO", activeUserDTO);
	 * 
	 * int roleId = activeUserDTO.getRole_id();
	 * 
	 * System.out.println("[UserServlet] Role ID = " + roleId);
	 * 
	 * if (roleId == 3) {
	 * 
	 * System.out.println("[UserServlet] Admin login detected");
	 * response.sendRedirect("AdminAppointmentServlet?page=dashboard"); //
	 * response.sendRedirect("AdminAppointmentServlet?page=dashboard");
	 * 
	 * } else if (roleId == 2) {
	 * 
	 * System.out.println("[UserServlet] Doctor login detected");
	 * 
	 * response.sendRedirect("DoctorServlet?page=dashboard");
	 * 
	 * } else {
	 * 
	 * System.out.println("[UserServlet] Patient login detected");
	 * 
	 * response.sendRedirect("admin/pati.jsp"); }
	 * 
	 * } else {
	 * 
	 * System.out.println("[UserServlet] Login failed");
	 * 
	 * request.setAttribute("status", "Failed"); request.setAttribute("Message",
	 * "Invalid username or password"); // request.setAttribute("redirectUrl",
	 * "Login.jsp"); request.setAttribute("redirectUrl", request.getContextPath() +
	 * "/auth/Login.jsp"); RequestDispatcher rd =
	 * request.getRequestDispatcher("public/Messages.jsp");
	 * 
	 * rd.forward(request, response);
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.out.println("[UserServlet] Error during login"); e.printStackTrace();
	 * 
	 * response.sendRedirect("Login.jsp");
	 * 
	 * }
	 * 
	 * }
	 */

	/*
	 * private void UserLogin(HttpServletRequest request, HttpServletResponse
	 * response) throws IOException {
	 * 
	 * String username = request.getParameter("username"); String password =
	 * request.getParameter("password");
	 * 
	 * try {
	 * 
	 * UserDTO activeUserDTO = userService.UserLogin(username);
	 * 
	 * if (activeUserDTO != null) {
	 * 
	 * String storedHash = activeUserDTO.getPassword();
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * // 🔐 PASSWORD CHECK (MOST IMPORTANT) if (BCrypt.checkpw(password,
	 * storedHash)) {
	 * 
	 * System.out.println("[UserServlet] Login successful");
	 * 
	 * request.getSession().setAttribute("activeUserDTO", activeUserDTO);
	 * 
	 * int roleId = activeUserDTO.getRole_id();
	 * 
	 * if (roleId == 3) {
	 * response.sendRedirect("AdminAppointmentServlet?page=dashboard");
	 * 
	 * } else if (roleId == 2) {
	 * response.sendRedirect("DoctorServlet?page=dashboard");
	 * 
	 * } else { response.sendRedirect("admin/pati.jsp"); }
	 * 
	 * } else {
	 * 
	 * // ❌ WRONG PASSWORD request.setAttribute("status", "Failed");
	 * request.setAttribute("Message", "Invalid password");
	 * request.setAttribute("redirectUrl", request.getContextPath() +
	 * "/auth/Login.jsp");
	 * 
	 * request.getRequestDispatcher("public/Messages.jsp").forward(request,
	 * response); }
	 * 
	 * } else {
	 * 
	 * // ❌ USER NOT FOUND request.setAttribute("status", "Failed");
	 * request.setAttribute("Message", "User not found");
	 * request.setAttribute("redirectUrl", request.getContextPath() +
	 * "/auth/Login.jsp");
	 * 
	 * request.getRequestDispatcher("public/Messages.jsp").forward(request,
	 * response); }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * response.sendRedirect("Login.jsp"); } }
	 * 
	 */

	private void UserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		try {

			UserDTO activeUserDTO = userService.UserLogin(username);

			if (activeUserDTO != null) {

				String storedHash = activeUserDTO.getPassword();

				// ✅ CASE 1: HASHED PASSWORD
				if (storedHash != null && storedHash.startsWith("$2")) {

					if (BCrypt.checkpw(password, storedHash)) {

						System.out.println("[UserServlet] Login successful");

						request.getSession().setAttribute("activeUserDTO", activeUserDTO);

						int roleId = activeUserDTO.getRole_id();

						if (roleId == 3) {
							response.sendRedirect("AdminAppointmentServlet?page=dashboard");

						} else if (roleId == 2) {
							response.sendRedirect("DoctorServlet?page=dashboard");

						} else {
							response.sendRedirect("admin/pati.jsp");
						}

					} else {

						request.setAttribute("status", "Failed");
						request.setAttribute("Message", "Invalid password");
						request.setAttribute("redirectUrl", request.getContextPath() + "/auth/Login.jsp");

						request.getRequestDispatcher("public/Messages.jsp").forward(request, response);
					}

				}
				// ⚠️ CASE 2: PLAIN PASSWORD (OLD USERS)
				else {

					if (password.equals(storedHash)) {

						// 🔥 AUTO UPGRADE TO HASH
						String newHash = BCrypt.hashpw(password, BCrypt.gensalt());

						userService.updatePassword(activeUserDTO.getId(), newHash);
						System.out.println("Password upgraded to BCrypt");

						request.getSession().setAttribute("activeUserDTO", activeUserDTO);

						int roleId = activeUserDTO.getRole_id();

						if (roleId == 3) {
							response.sendRedirect("AdminAppointmentServlet?page=dashboard");

						} else if (roleId == 2) {
							response.sendRedirect("DoctorServlet?page=dashboard");

						} else {
							response.sendRedirect("admin/pati.jsp");
						}

					} else {

						request.setAttribute("status", "Failed");
						request.setAttribute("Message", "Invalid password");
						request.setAttribute("redirectUrl", request.getContextPath() + "/auth/Login.jsp");

						request.getRequestDispatcher("public/Messages.jsp").forward(request, response);
					}
				}

			} else {

				request.setAttribute("status", "Failed");
				request.setAttribute("Message", "User not found");
				request.setAttribute("redirectUrl", request.getContextPath() + "/auth/Login.jsp");

				request.getRequestDispatcher("public/Messages.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("auth/Login.jsp");
		}
	}

	///////////////////////////////////
	private void FindUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException, NullPointerException {
		System.out.println("Find by id");
		int userId = Integer.parseInt(request.getParameter("userId"));

		try {
			UserDTO userDTO = userService.FindUser(userId);
			if (userDTO != null) {
				System.out.println("User found successfully.");
				System.out.println("First Name: " + userDTO.getFirst_name());
				System.out.println("Last name: " + userDTO.getLast_name());
				System.out.println("Email: " + userDTO.getEmail());
				System.out.println("Mobile No: " + userDTO.getMobile_no());
				System.out.println("Address: " + userDTO.getAddress());
				System.out.println("Age: " + userDTO.getAge());
				System.out.println("Gender: " + userDTO.getGender());

//				request.setAttribute("userDTOEdit", userDTO);

				request.setAttribute("userDTOEdit", userDTO);
				request.getRequestDispatcher("patient/PatientManage.jsp").forward(request, response);
			} else {
				System.out.println("User not found.");
				request.setAttribute("status", "Success");
				request.setAttribute("Message", "UserID is  not found :" + " " + userId);
				request.setAttribute("redirectUrl", "patient/PatientManage.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "Failed");
			request.setAttribute("Message", "Failed to find user due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "patient/PatientManage.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");
			rd.forward(request, response);
		}

	}

	private void UpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter("email").toLowerCase().trim();
		String mobile = request.getParameter("mobile_no").trim();
		int userId = Integer.parseInt(request.getParameter("userId"));

		UserDTO userDTO = new UserDTO();
		userDTO.setId(userId);
		userDTO.setFirst_name(request.getParameter("first_name"));
		userDTO.setLast_name(request.getParameter("last_name"));
		userDTO.setEmail(email);
		userDTO.setMobile_no(mobile);
		userDTO.setAge(Integer.parseInt(request.getParameter("age")));
		userDTO.setGender(request.getParameter("gender"));
		userDTO.setAddress(request.getParameter("address"));

		boolean hasError = false;

		// EMAIL
		if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[A-Za-z]{2,}$")) {
			request.setAttribute("emailError", "Invalid email format");
			hasError = true;
		}

		// MOBILE
		if (mobile == null || !mobile.matches("^[0-9]{10}$")) {
			request.setAttribute("mobileError", "Mobile must be 10 digits");
			hasError = true;
		}

		try {

			UserDTO existingEmailUser = userService.FindByEmail(email);
			if (existingEmailUser != null && existingEmailUser.getId() != userId) {
				request.setAttribute("emailError", "Email already used");
				hasError = true;
			}

			UserDTO existingMobileUser = userService.findByMobileNo(mobile);
			if (existingMobileUser != null && existingMobileUser.getId() != userId) {
				request.setAttribute("mobileError", "Mobile already used");
				hasError = true;
			}

			// ❌ ERROR → same page
			if (hasError) {
				request.setAttribute("userDTOEdit", userDTO);
				request.getRequestDispatcher("patient/PatientManage.jsp").forward(request, response);
				return;
			}

			// ✅ UPDATE
			/*
			 * int count = userService.UpdateUser(userDTO);
			 * 
			 * if (count > 0) {
			 * 
			 * UserDTO updatedUser = userService.FindUser(userId);
			 * request.getSession().setAttribute("activeUserDTO", updatedUser);
			 * 
			 * // ✅ SUCCESS → same page
			 * response.sendRedirect("patient/PatientManage.jsp?success=updated");
			 * 
			 * } else {
			 * 
			 * request.setAttribute("Message", "Update failed");
			 * request.setAttribute("userDTOEdit", userDTO);
			 * 
			 * request.getRequestDispatcher("patient/PatientManage.jsp") .forward(request,
			 * response); }
			 * 
			 * } catch (Exception e) { e.printStackTrace();
			 * 
			 * request.setAttribute("Message", "Something went wrong");
			 * request.setAttribute("userDTOEdit", userDTO);
			 * 
			 * request.getRequestDispatcher("patient/PatientManage.jsp") .forward(request,
			 * response); } }
			 */

			int count = userService.UpdateUser(userDTO);

			if (count > 0) {

				// 🔥 session update (important)
				UserDTO updatedUser = userService.FindUser(userId);
				request.getSession().setAttribute("activeUserDTO", updatedUser);

				// ✅ AJAX RESPONSE
				response.setContentType("text/plain");
				response.getWriter().write("success");

			} else {

				response.setContentType("text/plain");
				response.getWriter().write("error");
			}

		} catch (Exception e) {
			e.printStackTrace();

			response.setContentType("text/plain");
			response.getWriter().write("error");
		}
	}

	/*
	 * public void UpdateUser(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException, NumberFormatException{
	 * System.out.println(" UPDATE METHOD HIT"); String email =
	 * request.getParameter("email").toLowerCase().trim(); String mobile =
	 * request.getParameter("mobile_no").trim(); System.out.println("EMAIL = " +
	 * request.getParameter("email"));
	 * 
	 * if(email == null || !email.contains("@") || !email.contains(".")){
	 * 
	 * System.out.println("Invalid Email in UPDATE: " + email);
	 * 
	 * request.setAttribute("Message", "Enter valid email!");
	 * request.getRequestDispatcher("admin/pati.jsp") .forward(request, response);
	 * return; } if(mobile == null || !mobile.matches("^[0-9]{10}$")){
	 * 
	 * request.setAttribute("Message", "Invalid mobile number!");
	 * request.getRequestDispatcher("admin/pati.jsp") .forward(request, response);
	 * return; }
	 * 
	 * UserDTO userDTO = new UserDTO();
	 * userDTO.setId(Integer.parseInt(request.getParameter("userId")));
	 * 
	 * 
	 * userDTO.setFirst_name(request.getParameter("first_name"));
	 * userDTO.setLast_name(request.getParameter("last_name"));
	 * 
	 * userDTO.setEmail(email);
	 * userDTO.setMobile_no(request.getParameter("mobile_no"));
	 * userDTO.setAge(Integer.parseInt(request.getParameter("age")));
	 * 
	 * 
	 * userDTO.setGender(request.getParameter("gender"));
	 * userDTO.setAddress(request.getParameter("address"));
	 * 
	 * 
	 * // userDTO.setPassword(request.getParameter("password"));
	 * 
	 * try { int count = userService.UpdateUser(userDTO);
	 * System.out.println("sevlettttt");
	 * 
	 * if (count > 0) { System.out.println("User updated successfully");
	 * request.setAttribute("status", "Success"); request.setAttribute("Message",
	 * "User updated successfully :" + " " + userDTO.getId()); RequestDispatcher rd
	 * = request.getRequestDispatcher("Message.jsp");
	 * 
	 * request.setAttribute("redirectUrl", "HomePage.jsp"); rd.forward(request,
	 * response); } else { System.out.println("failed to updated user.");
	 * RequestDispatcher rd = request.getRequestDispatcher("Message.jsp");
	 * request.setAttribute("status", "Error"); request.setAttribute("Message",
	 * "failed to updated user."); request.setAttribute("redirectUrl", "Login.jsp");
	 * rd.forward(request, response); }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); request.setAttribute("status",
	 * "Error2"); request.setAttribute("Message",
	 * "Failed to update user data due to: " + e.getMessage());
	 * request.setAttribute("redirectUrl", "PatientManage.jsp"); RequestDispatcher
	 * rd = request.getRequestDispatcher("Message.jsp");
	 * 
	 * rd.forward(request, response); } }
	 */

	private void FindByMoblieNo(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String mobileNumber = request.getParameter("mobile_no");
		try {
			UserDTO userDTO = userService.findByMobileNo(mobileNumber);

			if (userDTO != null) {
				System.out.println("User find successfully.");
				System.out.println("Name " + userDTO.getFirst_name());
				System.out.println("Email " + userDTO.getEmail());
				System.out.println("MobileNo" + userDTO.getMobile_no());

				request.setAttribute("userDTO", userDTO);
				RequestDispatcher rd = request.getRequestDispatcher("PatientManage.jsp");
				rd.forward(request, response);

			} else {
				System.out.println("Failed to find.....");
				request.setAttribute("redirectUrl", "Login.jsp");

				request.setAttribute("status", "Failed");
				request.setAttribute("Message", "Failed to found mobile number: " + mobileNumber);

				request.setAttribute("redirectUrl", "admin/pati.jsp");
//				RequestDispatcher rd = request.getRequestDispatcher("Message.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");

				rd.forward(request, response);

			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("status", "Failed");
			request.setAttribute("Message",
					"Failed to found mobile number: " + mobileNumber + "kyoki" + e.getMessage());

			request.setAttribute("redirectUrl", "PatientManage.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");
			rd.forward(request, response);

		}

	}

	private void deleteById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, NumberFormatException {

		Integer userId = Integer.parseInt(request.getParameter("userId"));
		System.out.println("Deleting user by id : " + userId);

//		UserDTO userDTO=new UserDTO();
//		userDTO.setId(6);

		try {
			int count = userService.deleteById(userId);
//			int count = userService.deleteById(userDTO);
			if (count > 0) {
				request.setAttribute("status", "Success");
				request.setAttribute("Message", "User deleted successfully :" + userId);
				request.setAttribute("redirectUrl", "admin/pati.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");
				rd.forward(request, response);

			}

			else {
				request.setAttribute("Message", "No user found");

				request.setAttribute("redirectUrl", "PatientManage.jsp");
				RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");
				rd.forward(request, response);

			}
		} catch (Exception e) {
			System.err.println("Failed to delete use id: " + userId);

			e.printStackTrace();
			System.out.println("No user found.");
			request.setAttribute("status", "Success");
			request.setAttribute("Message", "Failed to delete user due to: " + e.getMessage());
			request.setAttribute("redirectUrl", "PatientManage.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("public/Messages.jsp");
			rd.forward(request, response);

			// TODO: handle exception
		}

	}

	private void showMyAppointments(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDTO user = (UserDTO) request.getSession().getAttribute("activeUserDTO");

		if (user == null) {
			response.sendRedirect("auth/Login.jsp");
			return;
		}
		System.out.println("Logged user ID = " + user.getId());

		try {

			AppointmentDAO appointmentDAO = new AppointmentDAO(dbUtil);

			/*
			 * int patientId = user.getId();
			 * 
			 * List<AppointmentDTO> list =
			 * appointmentDAO.getAppointmentsByPatientId(patientId);
			 */
			Connection con = dbUtil.getConnection();

			String sql = "SELECT id FROM patient WHERE user_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());

			ResultSet rs = ps.executeQuery();

			int patientId = 0;

			if (rs.next()) {
				patientId = rs.getInt("id");
				System.out.println("Patient ID = " + patientId);
			}

			List<AppointmentDTO> list = appointmentDAO.getAppointmentsByPatientId(patientId);
			request.setAttribute("appointmentList", list);
			System.out.println("Patient ID = " + patientId);
			System.out.println("List size = " + list.size());
			System.out.println("Logged User ID = " + user.getId());
			System.out.println("List size (patient) = " + list.size());
			RequestDispatcher rd = request.getRequestDispatcher("patient/PatientAppointments.jsp");

			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showPrescriptionHistory(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UserDTO user = (UserDTO) request.getSession().getAttribute("activeUserDTO");

		if (user == null) {
			response.sendRedirect("auth/Login.jsp");
			return;
		}

		try {

			DButil dbUtil = new DButil();
			PrescriptionDAO pdao = new PrescriptionDAO(dbUtil);

			// 🔥 SAME logic jo tu already use kar raha appointments me
			Connection con = dbUtil.getConnection();

			String sql = "SELECT id FROM patient WHERE user_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, user.getId());

			ResultSet rs = ps.executeQuery();

			int patientId = 0;

			if (rs.next()) {
				patientId = rs.getInt("id");
			}

			// 👉 FETCH prescriptions
			List<PrescriptionDTO> list = pdao.getPrescriptionsByPatientId(patientId);

			request.setAttribute("prescriptionList", list);

			RequestDispatcher rd = request.getRequestDispatcher("patient/PrescriptionHistory.jsp");

			rd.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void forgotPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String input = request.getParameter("identifier");

		try {

			UserDTO user = null;

			boolean isEmail = input.matches("^[^\\s@]+@[^\\s@]+\\.[A-Za-z]{2,}$");
			boolean isMobile = input.matches("^[0-9]{10}$");

			if (isEmail) {
				user = userService.FindByEmail(input);
			} else if (isMobile) {
				user = userService.findByMobileNo(input);
			} else {
				request.setAttribute("Message", "Invalid input");
				request.getRequestDispatcher("public/ForgotPassword.jsp").forward(request, response);
				return;
			}

			if (user != null) {

				int otp = (int) (Math.random() * 900000) + 100000;
				Timestamp expiry = new Timestamp(System.currentTimeMillis() + 5 * 60 * 1000);

				userService.saveOTP(user.getId(), String.valueOf(otp), expiry);

				System.out.println("OTP = " + otp); // testing ke liye
				EmailUtil.sendOTP(user.getEmail(), String.valueOf(otp));
				request.setAttribute("userId", user.getId());
				request.getRequestDispatcher("public/VerifyOTP.jsp").forward(request, response);

			} else {
				request.setAttribute("Message", "User not found");
				request.getRequestDispatcher("public/ForgotPassword.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void verifyOTP(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("userId"));
		String otp = request.getParameter("otp");

		try {

			boolean valid = userService.verifyOTP(userId, otp);

			if (valid) {

				//request.setAttribute("userId", userId);
				 request.getSession().setAttribute("resetUserId", userId);

				   System.out.println("SESSION SET USER ID = " + userId);
				request.getRequestDispatcher("public/ResetPassword.jsp").forward(request, response);

			} else {

				request.setAttribute("Message", "Invalid or expired OTP");
				request.getRequestDispatcher("public/VerifyOTP.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void resetPassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//int userId = Integer.parseInt(request.getParameter("userId"));
		Integer userId = (Integer) request.getSession().getAttribute("resetUserId");

		if (userId == null) {
			System.err.println("user is null");
		    response.sendRedirect("public/ForgotPassword.jsp");
		    return;
		}
		String newPassword = request.getParameter("password");

		try {

			String hash = BCrypt.hashpw(newPassword, BCrypt.gensalt());

			userService.updatePassword(userId, hash);
			request.getSession().removeAttribute("resetUserId");
			request.setAttribute("status", "Success");
			request.setAttribute("Message", "Password reset successful");
			request.setAttribute("redirectUrl", "auth/Login.jsp");

			request.getRequestDispatcher("public/Messages.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
