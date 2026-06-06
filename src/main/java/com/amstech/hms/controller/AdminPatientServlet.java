package com.amstech.hms.controller;

import java.io.IOException;
import java.util.List;

import com.amstech.hms.Dao.PatientDAO;
import com.amstech.hms.model.PatientDTO;
import com.amstech.hms.model.UserDTO;
import com.amstech.hms.util.DButil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/AdminPatientServlet")
public class AdminPatientServlet extends HttpServlet {

    private DButil dbUtil;
    private PatientDAO patientDAO;

    public AdminPatientServlet() {
        dbUtil = new DButil();
        patientDAO = new PatientDAO(dbUtil);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    //	getSession for secure 
    	UserDTO user = (UserDTO) request.getSession().getAttribute("activeUserDTO");

    	if (user == null) {
    	    response.sendRedirect("auth/Login.jsp");
    	    return;
    	}
    	
    	
        try {

            String action = request.getParameter("action");

            //DEFAULT → LOAD LIST

         if (action == null || action.trim().isEmpty()) {

             List<PatientDTO> list = patientDAO.getAllPatientsWithUserData();

             request.setAttribute("patientList", list);
          //   request.getRequestDispatcher("admin/AdminPatientManage.jsp")
             request.getRequestDispatcher("admin/AdminControls.jsp")

             
                    .forward(request, response);
         }

            // 🔥 ACTIVATE
            else if ("activate".equalsIgnoreCase(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                patientDAO.updateStatus(id, "ACTIVE");

                response.sendRedirect("AdminPatientServlet");
            }

            // 🔥 DEACTIVATE
            else if ("deactivate".equalsIgnoreCase(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                patientDAO.updateStatus(id, "INACTIVE");

                response.sendRedirect("AdminPatientServlet");
            }

            // 🔥 DELETE
            else if ("delete".equalsIgnoreCase(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                patientDAO.deletePatient(id);

                response.sendRedirect("AdminPatientServlet");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}