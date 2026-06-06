/*
 * package com.amstech.hms.controller;
 * 
 * 
 * import jakarta.servlet.RequestDispatcher; import
 * jakarta.servlet.ServletException; import
 * jakarta.servlet.annotation.WebServlet; import
 * jakarta.servlet.http.HttpServlet; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * import java.io.IOException; import java.util.List;
 * 
 * import com.amstech.hms.Dao.DoctorDAO; import com.amstech.hms.model.DoctorDTO;
 * import com.amstech.hms.util.DButil; import
 * com.amstech.hms.service.DoctorService; import
 * com.amstech.hms.service.UserService;
 * 
 * @WebServlet("/DoctorServlet") public class DoctorServlet extends HttpServlet
 * {
 * 
 * private static final long serialVersionUID = 1L;
 * 
 * private DButil dbUtil; private DoctorDAO doctorDAO; private DoctorService
 * doctorService;
 * 
 * public DoctorServlet() {
 * 
 * dbUtil = new DButil(); doctorDAO = new DoctorDAO(dbUtil); doctorService = new
 * DoctorService(doctorDAO); }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * String task = request.getParameter("task");
 * 
 * if (task == null) {
 * 
 * response.sendRedirect("admin/pati.jsp"); return; }
 * 
 * if (task.equalsIgnoreCase("findAll")) {
 * 
 * findAllDoctors(request, response); }
 * 
 * }
 * 
 * private void findAllDoctors(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * try { List<DoctorDTO> doctorList = doctorService.DoctorList();
 * System.out.println("Doctor list size: " + doctorList.size());
 * 
 * request.setAttribute("doctorList", doctorList);
 * 
 * RequestDispatcher rd =
 * request.getRequestDispatcher("/doctor/DoctorList.jsp");
 * 
 * rd.forward(request, response);
 * 
 * } catch (Exception e) {
 * 
 * e.printStackTrace();
 * 
 * request.setAttribute("status", "Failed"); request.setAttribute("Message",
 * "Unable to load doctors"); request.setAttribute("redirectUrl",
 * "admin/pati.jsp");
 * 
 * RequestDispatcher rd = request.getRequestDispatcher("Message.jsp");
 * rd.forward(request, response); } } }
 */

package com.amstech.hms.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amstech.hms.Dao.AppointmentDAO;
import com.amstech.hms.Dao.DoctorDAO;
import com.amstech.hms.model.AppointmentDTO;
import com.amstech.hms.model.DoctorDTO;
import com.amstech.hms.model.UserDTO;
import com.amstech.hms.util.DButil;
import com.amstech.hms.service.DoctorService;
import com.amstech.hms.service.UserService;

@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {

	private DButil dbUtil;
	private DoctorDAO doctorDAO;
	private DoctorService doctorService;
	private AppointmentDAO appointmentDAO;

	public DoctorServlet() {

		dbUtil = new DButil();
		doctorDAO = new DoctorDAO(dbUtil);
		doctorService = new DoctorService(doctorDAO);
		appointmentDAO = new AppointmentDAO(dbUtil);

		System.out.println("DoctorServlet Loaded");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response	)
			throws ServletException, IOException {

		System.out.println("DoctorServlet START");

		try {

			String page = request.getParameter("page");
			String task = request.getParameter("task");

			// DOCTOR LIST (optional)
			if ("findAll".equals(task)) {

				// List<DoctorDTO> doctorList = doctorService.DoctorList();
				
				
				String search = request.getParameter("search");
				
				
			  

			List<DoctorDTO> doctorList = doctorService.DoctorList(search);
		    request.setAttribute("doctorList", doctorList);


				RequestDispatcher rd = request.getRequestDispatcher("/doctor/DoctorList.jsp");

				rd.forward(request, response);
				return;
			}

			UserDTO user = (UserDTO) request.getSession().getAttribute("activeUserDTO");
			// if (user == null || user.getRole_id() != 2)

			if (user == null) {
				response.sendRedirect("auth/Login.jsp");
				return;
			}

			// int doctorId = user.getId();
			DoctorDTO doctor = doctorDAO.getDoctorByUserId(user.getId());

			if (doctor == null) {
				response.sendRedirect("doctor/DoctorProfile.jsp");
				return;
			}

			int doctorId = doctor.getId();
			System.out.println("Logged User ID = " + user.getId());
			System.out.println("Fetched Doctor ID = " + doctorId);

			System.out.println("Doctor ID check on = " + doctorId);

			// DASHBOARD
			if ("dashboard".equals(page)) {
				
				int total = appointmentDAO.getDoctorTotalAppointments(doctorId);
				int pending = appointmentDAO.getDoctorPendingAppointments(doctorId);

				request.setAttribute("totalAppointments", total);
				request.setAttribute("pendingAppointments", pending);
				System.out.println("Doctor Dashboard Loaded");
				// add new lines

				// DoctorDTO doctor = doctorDAO.getDoctorByUserId(user.getId());

				if (doctor == null || doctor.getSpecialization() == null
						|| doctor.getSpecialization().trim().isEmpty()) {
					response.sendRedirect("doctor/DoctorProfile.jsp");
					return;

				}
				if ("PENDING".equalsIgnoreCase(doctor.getStatus())) {
					response.sendRedirect("doctor/WaitingApproval.jsp");
					return;
				}

				if ("REJECTED".equalsIgnoreCase(doctor.getStatus())) {
					response.sendRedirect("doctor/Rejected.jsp");
					return;
				}

				request.setAttribute("doctor", doctor);

				RequestDispatcher rd = request.getRequestDispatcher("doctor/DoctorDashboard.jsp");

				rd.forward(request, response);
				return;
			}

			if ("appointments".equals(page) || "appointments".equals(task)) {
				// DOCTOR APPOINTMENTS
				System.out.println("Doctor Appointment page");
				//
				doctorDAO.getDoctorByUserId(user.getId()).getId();

//
				System.out.println("Doctor ID = " + doctorId);

				List<AppointmentDTO> list = appointmentDAO.getAppointmentsByDoctorId(doctorId);
				System.out.println("Doctor Appointment list loaded");
				System.out.println("List size = " + list.size());

				request.setAttribute("appointmentList", list);

				RequestDispatcher rd = request.getRequestDispatcher("doctor/DoctorAppointment.jsp");

				rd.forward(request, response);
//         /   response.sendRedirect("DoctorServlet?page=dashboard");
				return;
			}

			
			
			/*
			 * if ("updateAppointment".equals(page)) {
			 * 
			 * int id = Integer.parseInt(request.getParameter("id")); String newStatus =
			 * request.getParameter("status");
			 * 
			 * // 🔹 Logged doctor
			 * 
			 * 
			 * if (doctor == null) { response.sendRedirect("doctor/DoctorProfile.jsp");
			 * return; }
			 * 
			 * 
			 * // 🔹 Appointment fetch AppointmentDTO appointment =
			 * appointmentDAO.getAppointmentById(id); if (appointment == null) {
			 * response.sendRedirect("DoctorServlet?page=appointments"); return; }
			 * 
			 * if (appointment.getDoctor_id() != doctorId) {
			 * response.sendRedirect("DoctorServlet?page=appointments"); return; }
			 * 
			 * if (!"APPROVED".equalsIgnoreCase(appointment.getStatus())) {
			 * response.sendRedirect("DoctorServlet?page=appointments"); return; }
			 * 
			 * if (!("COMPLETED".equalsIgnoreCase(newStatus) ||
			 * "CANCELLED".equalsIgnoreCase(newStatus))) {
			 * response.sendRedirect("DoctorServlet?page=appointments"); return; }
			 * 
			 * // ✅ Update appointmentDAO.updateAppointmentStatus(id, newStatus);
			 * 
			 * response.sendRedirect("DoctorServlet?page=appointments"); return; }
			 */
			
			if ("updateAppointment".equals(page)) {

			    int id = Integer.parseInt(request.getParameter("id"));
			    String newStatus = request.getParameter("status");

			    AppointmentDTO appointment = appointmentDAO.getAppointmentById(id);

			    if (appointment == null) {
			        response.sendRedirect("DoctorServlet?page=appointments");
			        return;
			    }

			    // wrong doctor
			    if (appointment.getDoctor_id() != doctorId) {
			        response.sendRedirect("DoctorServlet?page=appointments");
			        return;
			    }

			    String currentStatus = appointment.getStatus();

			    //  STATUS FLOW

			    if ("PENDING".equalsIgnoreCase(currentStatus)) {
			        if (!("APPROVED".equalsIgnoreCase(newStatus) || "CANCELLED".equalsIgnoreCase(newStatus))) {
			            response.sendRedirect("DoctorServlet?page=appointments");
			            return;
			        }
			    }
				/*
				 * else if ("APPROVED".equalsIgnoreCase(currentStatus)) { if
				 * (!"COMPLETED".equalsIgnoreCase(newStatus)) {
				 * response.sendRedirect("DoctorServlet?page=appointments"); return; } }
				 */
			    
			    
			    else if ("APPROVED".equalsIgnoreCase(currentStatus)) {

			        if (!"IN_PROGRESS".equalsIgnoreCase(newStatus)
			                && !"CANCELLED".equalsIgnoreCase(newStatus)) {

			            response.sendRedirect("DoctorServlet?page=appointments");
			            return;
			        }
			    }

			    else if ("IN_PROGRESS".equalsIgnoreCase(currentStatus)) {

			        if (!"COMPLETED".equalsIgnoreCase(newStatus)) {

			            response.sendRedirect("DoctorServlet?page=appointments");
			            return;
			        }
			    }	
			    
			    
			    else {
			        // COMPLETED / CANCELLED → no change
			        response.sendRedirect("DoctorServlet?page=appointments");
			        return;
			    }

			    //  update
			    appointmentDAO.updateAppointmentStatus(id, newStatus);

			    response.sendRedirect("DoctorServlet?page=appointments");
			    return;
			}
			
			
			
			
		} catch (Exception e) {

			System.out.println("Error in DoctorServlet");
			e.printStackTrace();

		}
	}
	
	/*
	 * public List<DoctorDTO> getFilteredDoctors(String search, String
	 * specialization, String date) throws Exception {
	 * 
	 * List<DoctorDTO> list = new ArrayList<>();
	 * 
	 * StringBuilder sql = new StringBuilder(""" SELECT d.id, d.status,
	 * u.first_name, u.last_name, u.email, u.mobile_no, d.specialization,
	 * d.shift_time, d.user_id FROM doctor d JOIN user u ON d.user_id = u.id WHERE
	 * d.status = 'ACTIVE' """);
	 * 
	 * List<Object> params = new ArrayList<>();
	 * 
	 * // 🔍 SEARCH if (search != null && !search.trim().isEmpty()) {
	 * sql.append(" AND u.first_name LIKE ?"); params.add("%" + search.trim() +
	 * "%"); }
	 * 
	 * // 🏥 SPECIALIZATION if (specialization != null &&
	 * !specialization.trim().isEmpty()) { sql.append(" AND d.specialization = ?");
	 * params.add(specialization.trim()); }
	 * 
	 * // 📅 DATE (availability filter) if (date != null && !date.trim().isEmpty())
	 * { sql.append(""" AND d.id NOT IN ( SELECT doctor_id FROM appointment WHERE
	 * appointment_date = ? ) """); params.add(date.trim()); }
	 * 
	 * Connection con = dbUtil.getConnection(); PreparedStatement ps =
	 * con.prepareStatement(sql.toString());
	 * 
	 * // 🔥 dynamic params set for (int i = 0; i < params.size(); i++) {
	 * ps.setObject(i + 1, params.get(i)); }
	 * 
	 * ResultSet rs = ps.executeQuery();
	 * 
	 * while (rs.next()) { DoctorDTO d = new DoctorDTO();
	 * 
	 * d.setId(rs.getInt("id"));
	 * d.setSpecialization(rs.getString("specialization"));
	 * d.setShift_time(rs.getString("shift_time"));
	 * d.setFirst_name(rs.getString("first_name"));
	 * d.setLast_name(rs.getString("last_name")); d.setEmail(rs.getString("email"));
	 * d.setMobile_no(rs.getString("mobile_no"));
	 * d.setUser_id(rs.getInt("user_id")); d.setStatus(rs.getString("status"));
	 * 
	 * list.add(d); }
	 * 
	 * return list; }
	 */
}
