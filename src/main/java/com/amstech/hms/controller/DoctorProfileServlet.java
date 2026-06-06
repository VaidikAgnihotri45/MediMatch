	package com.amstech.hms.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.amstech.hms.model.UserDTO;
import com.amstech.hms.util.DButil;

@WebServlet("/DoctorProfileServlet")
public class DoctorProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🔹 Form data
        String specialization = request.getParameter("specialization");
        String shift = request.getParameter("shift_time");

        HttpSession session = request.getSession();

        try {
            // 🔹 Logged-in user check
            UserDTO user = (UserDTO) session.getAttribute("activeUserDTO");

            if (user == null || user.getRole_id() != 2) {
                response.sendRedirect("auth/Login.jsp");
                return;
            }

            int userId = user.getId();

            // 🔹 DB connection
            DButil db = new DButil();
            Connection con = db.getConnection();
            
         // 🔍 STEP 1: check if doctor already exists
            String checkSql = "SELECT * FROM doctor WHERE user_id=?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setInt(1, userId);
            ResultSet rs = checkPs.executeQuery();

            // 🔥 STEP 2: agar record nahi hai tabhi insert karo
            if (!rs.next()) {

                String insertSql = "INSERT INTO doctor (user_id, status) VALUES (?, 'PENDING')";
                PreparedStatement insertPs = con.prepareStatement(insertSql);
                insertPs.setInt(1, userId);
                insertPs.executeUpdate();

                System.out.println("Doctor row created");
            }
 
            // 🔹 Update doctor profile
            String sql = "UPDATE doctor SET specialization=?, shift_time=? WHERE user_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, specialization);
            ps.setString(2, shift);
            ps.setInt(3, userId);

            int rows = ps.executeUpdate();
            
            if(rows > 0){
                System.out.println("Profile updated successfully");
            } else {
                System.out.println("No row updated (doctor record missing)");
            }

            // 🔹 Debug (optional)
            System.out.println("Doctor profile updated rows: " + rows);

            // 🔹 Redirect to dashboard
            response.sendRedirect("DoctorServlet?page=dashboard");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}