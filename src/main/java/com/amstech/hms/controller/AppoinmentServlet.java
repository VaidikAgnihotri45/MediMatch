/*
 * //package com.amstech.hms.controller; // //import
 * jakarta.servlet.RequestDispatcher; //import jakarta.servlet.ServletException;
 * //import jakarta.servlet.annotation.WebServlet; //import
 * jakarta.servlet.http.HttpServlet; //import
 * jakarta.servlet.http.HttpServletRequest; //import
 * jakarta.servlet.http.HttpServletResponse; // //import java.io.IOException; //
 * //import com.amstech.hms.Dao.AppointmentDAO; //import
 * com.amstech.hms.model.AppointmentDTO; //import com.amstech.hms.model.UserDTO;
 * //import com.amstech.hms.util.DButil; // //@SuppressWarnings("serial")
 * //@WebServlet("/AppointmentServlet") //public class AppoinmentServlet extends
 * HttpServlet { // // private DButil dbUtil; // private AppointmentDAO
 * appointmentDAO; // // public AppoinmentServlet() { // // dbUtil = new
 * DButil(); // appointmentDAO = new AppointmentDAO(dbUtil); // } // //
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) // throws ServletException, IOException { //
 * System.out.println("Method:post 21212121."); // // // try { // // String
 * doctorIdStr = request.getParameter("doctorId"); //
 * System.out.println("Doctor ID received = " + doctorIdStr); // String date =
 * request.getParameter("date"); // String time = request.getParameter("time");
 * // String reason = request.getParameter("reason"); // // // UserDTO
 * activeUser = (UserDTO) request.getSession().getAttribute("activeUserDTO"); //
 * // if(activeUser == null){ // response.sendRedirect("login.jsp"); // return;
 * // } // int patientId = activeUser.getId(); // // int doctorId = 0; // // if
 * (doctorIdStr != null && !doctorIdStr.isEmpty()) { // throw new
 * Exception("Doctor not selected"); // // } // doctorId =
 * Integer.parseInt(doctorIdStr); // // AppointmentDTO dto = new
 * AppointmentDTO(); // // dto.setDoctor_id(doctorId); //
 * dto.setPatient_id(patientId); // // dto.setAppointment_date(date); //
 * dto.setAppointment_time(time); // dto.setReason(reason); //
 * dto.setStatus("Booked"); // // int count =
 * appointmentDAO.bookAppointment(dto); // // if (count > 0) { // //
 * response.sendRedirect("patient/pati.jsp"); // } // // } catch (Exception e) {
 * // // e.printStackTrace(); // // request.setAttribute("status", "Failed"); //
 * request.setAttribute("Message", "Unable to book appointment"); // //
 * RequestDispatcher rd = request.getRequestDispatcher("Message.jsp"); //
 * rd.forward(request, response); // } // } //}
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * package com.amstech.hms.controller;
 * 
 * import jakarta.servlet.RequestDispatcher; import
 * jakarta.servlet.ServletException; import
 * jakarta.servlet.annotation.WebServlet; import
 * jakarta.servlet.http.HttpServlet; import
 * jakarta.servlet.http.HttpServletRequest; import
 * jakarta.servlet.http.HttpServletResponse;
 * 
 * import java.io.IOException;
 * 
 * import com.amstech.hms.Dao.AppointmentDAO; import
 * com.amstech.hms.model.AppointmentDTO; import com.amstech.hms.model.UserDTO;
 * import com.amstech.hms.util.DButil;
 * 
 * @SuppressWarnings("serial")
 * 
 * @WebServlet("/AppointmentServlet") public class AppoinmentServlet extends
 * HttpServlet {
 * 
 * 
 * private DButil dbUtil; private AppointmentDAO appointmentDAO;
 * 
 * public AppoinmentServlet() {
 * 
 * dbUtil = new DButil(); appointmentDAO = new AppointmentDAO(dbUtil); }
 * 
 * protected void doPost(HttpServletRequest request, HttpServletResponse
 * response) throws ServletException, IOException {
 * 
 * System.out.println("Method: POST AppointmentServlet");
 * 
 * try {
 * 
 * String doctorIdStr = request.getParameter("doctorId");
 * System.out.println("Doctor ID received = " + doctorIdStr);
 * 
 * String date = request.getParameter("date"); String time =
 * request.getParameter("time"); String reason = request.getParameter("reason");
 * 
 * // session user UserDTO activeUser = (UserDTO)
 * request.getSession().getAttribute("activeUserDTO");
 * 
 * if (activeUser == null) { response.sendRedirect("login.jsp"); return; }
 * 
 * int patientId = activeUser.getId();
 * 
 * // doctor validation if (doctorIdStr == null || doctorIdStr.trim().isEmpty())
 * { throw new Exception("Doctor not selected"); }
 * 
 * int doctorId = Integer.parseInt(doctorIdStr);
 * 
 * AppointmentDTO dto = new AppointmentDTO();
 * 
 * dto.setDoctor_id(doctorId); dto.setPatient_id(patientId);
 * dto.setAppointment_date(date); dto.setAppointment_time(time);
 * dto.setReason(reason); dto.setStatus("Booked");
 * 
 * int count = appointmentDAO.bookAppointment(dto);
 * 
 * if (count > 0) {
 * 
 * System.out.println("Appointment booked successfully");
 * 
 * response.sendRedirect("patient/pati.jsp");
 * 
 * } else {
 * 
 * throw new Exception("Unable to book appointment");
 * 
 * }
 * 
 * } catch (Exception e) {
 * 
 * e.printStackTrace();
 * 
 * request.setAttribute("status", "Failed"); request.setAttribute("Message",
 * "Unable to book appointment");
 * 
 * RequestDispatcher rd = request.getRequestDispatcher("Message.jsp");
 * rd.forward(request, response); } }
 * 
 * 
 * }
 * 
 * 
 * 
 
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
import com.amstech.hms.Dao.PatientDAO;
import com.amstech.hms.model.AppointmentDTO;
import com.amstech.hms.model.UserDTO;
import com.amstech.hms.util.DButil;
import com.amstech.hms.Dao.DoctorDAO;
import com.amstech.hms.model.DoctorDTO;


@SuppressWarnings("serial")
@WebServlet("/AppointmentServlet")
public class AppoinmentServlet extends HttpServlet {

private DButil dbUtil;
private AppointmentDAO appointmentDAO;
private PatientDAO patientDAO;
private DoctorDAO doctorDAO;

public AppoinmentServlet() {

    dbUtil = new DButil();
    doctorDAO = new DoctorDAO(dbUtil);
    appointmentDAO = new AppointmentDAO(dbUtil);
    patientDAO = new PatientDAO(dbUtil);
}

protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    System.out.println("AppointmentServlet doGet called");

    List<DoctorDTO> doctorList = null;
	try {
		String search = request.getParameter("search");

		doctorList = doctorDAO.getAllDoctorsFull(search);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} // ✅ FIX

    request.setAttribute("doctorList", doctorList);

    RequestDispatcher rd = request.getRequestDispatcher("/public/BookAppointment.jsp"); // ✅ FIX
    rd.forward(request, response);
}

protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
	
	
	

    System.out.println("Method: POST AppointmentServlet");

    try {

        String doctorIdStr = request.getParameter("doctorId");
        System.out.println("Doctor ID received = " + doctorIdStr);

        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String reason = request.getParameter("reason");

        // session user
        UserDTO activeUser = (UserDTO) request.getSession().getAttribute("activeUserDTO");

        if (activeUser == null) {
            response.sendRedirect("auth/Login.jsp");
            return;
        }

        // get patient id using user id
        int patientId = patientDAO.getPatientIdByUserId(activeUser.getId());
        System.out.println("Patient ID = " + patientId);
        
        
        if (patientId == 0) {
            throw new Exception("Patient record not found");
        }

        // doctor validation
        
		/*
		 * if (doctorIdStr == null || doctorIdStr.trim().isEmpty()) { throw new
		 * Exception("Doctor not selected"); }
		 */
        if (doctorIdStr == null || doctorIdStr.trim().isEmpty()) {
        	String search = request.getParameter("search");

            // 🔥 doctor list bhejna (dropdown ke liye)
            List<DoctorDTO> doctorList = doctorDAO.getAllDoctorsFull(search);
            
            
            request.setAttribute("doctorList", doctorList);

            request.setAttribute("errorMsg", "Please select a doctor");

            RequestDispatcher rd = request.getRequestDispatcher("public/BookAppointment.jsp");
            rd.forward(request, response);

            return;
        }
        
        

        int doctorId = Integer.parseInt(doctorIdStr);
        DoctorDTO doctor = doctorDAO.getDoctorById(doctorId);

        if (doctor == null || !"ACTIVE".equalsIgnoreCase(doctor.getStatus())) {

            request.setAttribute("errorMsg", "Doctor is not available for booking");

            RequestDispatcher rd = request.getRequestDispatcher("public/BookAppointment.jsp");
            rd.forward(request, response);

            return;
        }
        
        boolean alreadyBooked = appointmentDAO.isSlotBooked(doctorId, date, time);

        if (alreadyBooked) {

            request.setAttribute("errorMsg", "Slot already booked. Please choose another time.");

            RequestDispatcher rd = request.getRequestDispatcher("public/BookAppointment.jsp");
            rd.forward(request, response);

            return;
        }

        AppointmentDTO dto = new AppointmentDTO();

        dto.setDoctor_id(doctorId);
//        dto.setPatient_id(activeUser.getId());
       dto.setPatient_id(patientId);
        dto.setAppointment_date(date);
        dto.setAppointment_time(time);
        dto.setReason(reason);
        dto.setStatus("PENDING");
        
        System.out.println("Patient ID  check  = " + patientId);
     
        
        //  int count = appointmentDAO.bookAppointment(dto);

         int count  = appointmentDAO.bookAppointment(dto);
        if (count > 0) {
        	

            System.out.println("Appointment booked successfully");

            request.setAttribute("successMsg", "Appointment booked successfully!");

            RequestDispatcher rd = request.getRequestDispatcher("public/BookAppointment.jsp");
            rd.forward(request, response);

        

        } else {
        	
        	request.setAttribute("errorMsg", "Unable to book appointment. Please try again.");

        	RequestDispatcher rd = request.getRequestDispatcher("public/BookAppointment.jsp");
        	rd.forward(request, response);

			/*
			 * throw new Exception("Unable to book appointment");
			 */
        }

    } catch (Exception e) {

        e.printStackTrace();

        request.setAttribute("status", "Failed2");
        request.setAttribute("Message", "Unable to book appointment");
        request.setAttribute("errorMsg", "Unable to book appointment. Please try again.");
        RequestDispatcher rd = request.getRequestDispatcher("Message.jsp");
        rd.forward(request, response);
    }
}


}





