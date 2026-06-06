/*
 * package com.amstech.hms.controller;
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
 * import com.amstech.hms.Dao.AppointmentDAO; import
 * com.amstech.hms.model.AppointmentDTO; import com.amstech.hms.util.DButil;
 * 
 * @WebServlet("/AdminAppointmentServlet") public class AdminAppointmentServlet
 * extends HttpServlet {
 * 
 * private DButil dbUtil; private AppointmentDAO appointmentDAO;
 * 
 * public AdminAppointmentServlet() {
 * 
 * dbUtil = new DButil(); appointmentDAO = new AppointmentDAO(dbUtil);
 * System.out.println("AdminAppointmentServlet called"); }
 * 
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * try { System.out.println("AdminAppointmentServlet before list");
 * List<AppointmentDTO> list = appointmentDAO.getAllAppointments();
 * System.out.println("AdminAppointmentServlet list");
 * 
 * request.setAttribute("appointmentList", list);
 * 
 * RequestDispatcher rd =
 * request.getRequestDispatcher("admin/AppointmentManage.jsp");
 * rd.forward(request, response);
 * 
 * } catch (Exception e) {
 * 
 * e.printStackTrace();
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * 
 * }
 */

package com.amstech.hms.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import com.amstech.hms.Dao.AppointmentDAO;
import com.amstech.hms.Dao.DoctorDAO;
import com.amstech.hms.model.AppointmentDTO;
import com.amstech.hms.model.DoctorDTO;
import com.amstech.hms.model.UserDTO;
import com.amstech.hms.util.DButil;

@WebServlet("/AdminAppointmentServlet")
public class AdminAppointmentServlet extends HttpServlet {

private DButil dbUtil;
private AppointmentDAO appointmentDAO;
private DoctorDAO doctorDAO;

public AdminAppointmentServlet() {

    dbUtil = new DButil();
    appointmentDAO = new AppointmentDAO(dbUtil);
    doctorDAO =new  DoctorDAO(dbUtil);
    
    

    System.out.println("AdminAppointmentServlet loaded");
}

/*
 * protected void doGet(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * System.out.println("AdminAppointmentServlet -> doGet method started");
 * 
 * try { //
 * 
 * // String action = request.getParameter("action");
 * System.out.println("Action received = " + action);
 * 
 * // UPDATE STATUS if ("update".equals(action)) {
 * 
 * int id = Integer.parseInt(request.getParameter("id")); String status =
 * request.getParameter("status");
 * System.out.println("Updating Appointment ID = " + id);
 * System.out.println("New Status = " + status);
 * 
 * appointmentDAO.updateAppointmentStatus(id, status);
 * System.out.println("Appointment status updated successfully");
 * 
 * response.sendRedirect("AdminAppointmentServlet"); return; }
 * 
 * // SHOW APPOINTMENT LIST System.out.println("Fetching appointment list...");
 * 
 * List<AppointmentDTO> list = appointmentDAO.getAllAppointments();
 * 
 * request.setAttribute("appointmentList", list);
 * 
 * RequestDispatcher rd =
 * request.getRequestDispatcher("admin/AppointmentManage.jsp");
 * 
 * rd.forward(request, response);
 * 
 * } catch (Exception e) {
 * System.out.println("Error in AdminAppointmentServlet");
 * 
 * e.printStackTrace();
 * 
 * }
 * 
 * }
 * 
 * }
 */
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    System.out.println("[AdminAppointmentServlet] doGet started");

    // 🔐 AUTH CHECK
    UserDTO user = (UserDTO) request.getSession().getAttribute("activeUserDTO");
	
	  if (user == null || user.getRole_id() != 3) {
	  response.sendRedirect("auth/Login.jsp"); return; }
	 
    try {

        String action = request.getParameter("action");
        String page = request.getParameter("page");

        System.out.println("Action = " + action);
        System.out.println("Page = " + page);

        
		
		/*
		 * if (page == null && action == null) {
		 * response.sendRedirect("AdminAppointmentServlet?page=dashboard"); return; }
		 */
        
        
        // =========================
        // 🔵 1. DASHBOARD (FIRST PRIORITY)
        // =========================
        if ("dashboard".equals(page)) {

            int totalAppointments = appointmentDAO.getTotalAppointments();
            int totalDoctors = doctorDAO.getTotalDoctors();
            int totalPatients = doctorDAO.getTotalPatients();
            
            System.out.println("🔥 DASHBOARD BLOCK RUNNING");
            System.out.println("Doctors = " + totalDoctors);
            System.out.println("Patients = " + totalPatients);
            System.out.println("Appointments = " + totalAppointments);

            request.setAttribute("totalAppointments", totalAppointments);
            request.setAttribute("totalDoctors", totalDoctors);
            request.setAttribute("totalPatients", totalPatients);

            RequestDispatcher rd =
                request.getRequestDispatcher("admin/AdminDashboard.jsp");

            rd.forward(request, response);
            return;
        }

        // =========================
        // 🔴 2. UPDATE APPOINTMENT
        // =========================
        if ("update".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));
            String status = request.getParameter("status");

            if (!"Approved".equalsIgnoreCase(status) &&
                !"Cancelled".equalsIgnoreCase(status)) {

                response.sendRedirect("AdminAppointmentServlet?action=list");
                return;
            }

            appointmentDAO.updateAppointmentStatus(id, status);

            response.sendRedirect("AdminAppointmentServlet?action=list");
            return;
        }

        // =========================
        // 🟣 3. DOCTOR MANAGEMENT
        // =========================
        if ("pendingDoctors".equals(action)) {

            List<DoctorDTO> list = doctorDAO.getPendingDoctors();

            request.setAttribute("doctorList", list);

            RequestDispatcher rd =
                request.getRequestDispatcher("doctor/DoctorManage.jsp");

            rd.forward(request, response);
            return;
        }

        if ("approveDoctor".equals(action)) {

            int id = Integer.parseInt(request.getParameter("id"));

            doctorDAO.updateDoctorStatusByUserId(id, "ACTIVE");

            response.sendRedirect("AdminAppointmentServlet?action=pendingDoctors");
            return;
        }
     //   
        if ("reports".equals(page)) {

            List<AppointmentDTO> list = appointmentDAO.getAllAppointments();

            request.setAttribute("reportList", list);

            request.setAttribute("totalAppointments", appointmentDAO.getTotalAppointments());
            request.setAttribute("totalDoctors", doctorDAO.getTotalDoctors());
            request.setAttribute("totalPatients", doctorDAO.getTotalPatients());

            RequestDispatcher rd =
            request.getRequestDispatcher("admin/SystemReports.jsp");

            rd.forward(request, response);
            return;
        }

        // =========================
        // 🟢 4. DEFAULT (LIST)
        // =========================
        List<AppointmentDTO> list = appointmentDAO.getAllAppointments();

        request.setAttribute("appointmentList", list);

        RequestDispatcher rd =
            request.getRequestDispatcher("admin/AppointmentManage.jsp");

        rd.forward(request, response);

    } catch (Exception e) {

        System.out.println("[AdminAppointmentServlet] Error occurred");
        e.printStackTrace();
    }
}
}




