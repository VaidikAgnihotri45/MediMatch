	
package com.amstech.hms.controller;
		
		import jakarta.servlet.*;
		import jakarta.servlet.annotation.WebServlet;
		import jakarta.servlet.http.*;
		import java.io.IOException;
		import com.itextpdf.text.Image;
		import com.itextpdf.text.Rectangle;
		import com.itextpdf.text.BaseColor;
		import com.itextpdf.text.Document;
		import com.itextpdf.text.Element;
		import com.itextpdf.text.Font;
		import com.itextpdf.text.Paragraph;
		import com.itextpdf.text.Phrase;
		import com.itextpdf.text.pdf.PdfPCell;
		import com.itextpdf.text.pdf.PdfPTable;
		import com.itextpdf.text.pdf.PdfWriter;
		import com.amstech.hms.Dao.AppointmentDAO;
		import com.amstech.hms.Dao.PrescriptionDAO;
		import com.amstech.hms.model.AppointmentDTO;
		import com.amstech.hms.model.PrescriptionDTO;
		import com.amstech.hms.model.UserDTO;
		import com.amstech.hms.util.DButil;
		
		@WebServlet("/PrescriptionServlet")
		public class PrescriptionServlet extends HttpServlet {
		
			
			protected void doGet(HttpServletRequest request, HttpServletResponse response)
			        throws ServletException, IOException {
		
			    String task = request.getParameter("task");
		
			    // 🔥 PDF case FIRST
			    if ("downloadPDF".equals(task)) {
			        generatePDF(request, response);
			        return;
			    }
			    if ("view".equals(task)) {
			        viewPrescription(request, response);
			        return;
			    }
		
			    // 🔥 Normal flow (Add Prescription page)
			    String appointmentParam = request.getParameter("appointmentId");
		
			    if (appointmentParam != null) {
			        int appointmentId = Integer.parseInt(appointmentParam);
		
			        request.setAttribute("appointmentId", appointmentId);
		
			        RequestDispatcher rd = request.getRequestDispatcher("doctor/AddPrescription.jsp");
			        rd.forward(request, response);
			    } else {
			        response.sendRedirect("DoctorServlet?page=appointments");
			    }
			}
			
			
		    protected void doPost(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		
		        try {
		
		            int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
		            String medicines = request.getParameter("medicines");
		            String notes = request.getParameter("notes");
		            System.out.println("Medicines: " + medicines);
		            System.out.println("Notes: " + notes);
		            DButil dbUtil = new DButil();
		
		            AppointmentDAO appointmentDAO = new AppointmentDAO(dbUtil);
		            PrescriptionDAO pdao = new PrescriptionDAO(dbUtil);
		
		            // 👉 doctor from session
		            UserDTO user = (UserDTO) request.getSession().getAttribute("activeUserDTO");
		            int doctorId = user.getId();
		
		            // 👉 patient from appointment
		            AppointmentDTO appointment = appointmentDAO.getAppointmentById(appointmentId);
		            int patientId = appointment.getPatient_id();
		
		            // 👉 SAVE prescription
		            pdao.addPrescription(appointmentId, doctorId, patientId, medicines, notes);
		
		            // 👉 UPDATE status
		  //          appointmentDAO.updateAppointmentStatus(appointmentId, "COMPLETED");
		            
		            
		  		      appointmentDAO.updateAppointmentStatus(appointmentId, "IN_PROGRESS");

		            response.sendRedirect("DoctorServlet?page=appointments");
		
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    @SuppressWarnings("unused")
		    private void generatePDF(HttpServletRequest request, HttpServletResponse response)
		            throws ServletException, IOException {
		
		        try {
		
		            // 🔐 Security
		            UserDTO user = (UserDTO) request.getSession().getAttribute("activeUserDTO");
		
		            if (user == null) {
		                response.sendRedirect("auth/Login.jsp");
		                return;
		            }
		
		            int id = Integer.parseInt(request.getParameter("id"));
		
		            DButil dbUtil = new DButil();
		            PrescriptionDAO dao = new PrescriptionDAO(dbUtil);
		
		            PrescriptionDTO p = dao.getPrescriptionById(id);
		
		            if (p == null) {
		                response.getWriter().write("No prescription found");
		                return;
		            }
		
		            response.setContentType("application/pdf");
		            response.setHeader("Content-Disposition", "attachment; filename=prescription.pdf");
		
		            Document document = new Document();
		
		            PdfWriter.getInstance(document, response.getOutputStream());
		
		            document.open();
		
		            // 🔥 COLORS
		            BaseColor primaryBlue = new BaseColor(10, 79, 175);
		            BaseColor lightGray = new BaseColor(240, 240, 240);
		
		            // 🔥 FONTS
		            Font hospitalFont = new Font(Font.FontFamily.HELVETICA, 24, Font.BOLD, primaryBlue);
		
		            Font subFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.DARK_GRAY);
		
		            Font headingFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, primaryBlue);
		
		            Font labelFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, primaryBlue);
		
		            Font valueFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
		
		            Font footerFont = new Font(Font.FontFamily.HELVETICA, 11, Font.NORMAL, BaseColor.DARK_GRAY);
		
		            // =====================================================
		            // 🏥 HEADER
		            // =====================================================
		
					/*
					 * Paragraph hospital = new Paragraph("NewLife Hospital", hospitalFont);
					 * hospital.setAlignment(Element.ALIGN_CENTER); hospital.setSpacingAfter(5);
					 * document.add(hospital);
					 * 
					 * Paragraph tagline = new Paragraph( "Care You Can Trust, Health You Deserve",
					 * subFont);
					 * 
					 * tagline.setAlignment(Element.ALIGN_CENTER); tagline.setSpacingAfter(10);
					 * 
					 * document.add(tagline);
					 * 
					 * Paragraph contact = new Paragraph(
					 * "📞 +91 98765 43210    |    ✉ info@newlifehospital.com", subFont);
					 * 
					 * contact.setAlignment(Element.ALIGN_CENTER); contact.setSpacingAfter(20);
					 * 
					 * document.add(contact);
					 */
		            
		            
		         // =====================================================
		         // 🏥 HEADER WITH LOGO
		         // =====================================================
		
		         PdfPTable headerTable = new PdfPTable(2);
		         headerTable.setWidthPercentage(100);
		
		         float[] headerWidth = {2f, 5f};
		         headerTable.setWidths(headerWidth);
		
		         // 🔥 LOGO
		
		         String logoPath = getServletContext()
		                 .getRealPath("/images/logo.png");
		
		         Image logo = Image.getInstance(logoPath);
		
		         logo.scaleToFit(80, 80);
		
		         PdfPCell logoCell = new PdfPCell(logo);
		
		         logoCell.setBorder(Rectangle.NO_BORDER);
		         logoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
		         logoCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		
		         headerTable.addCell(logoCell);
		
		         // 🔥 HOSPITAL INFO
		
		         Paragraph hospital = new Paragraph("NewLife Hospital", hospitalFont);
		
		         Paragraph tagline = new Paragraph(
		                 "Care You Can Trust, Health You Deserve",
		                 subFont);
		
		         Paragraph contact = new Paragraph(
		                 "Phone: +91 98765 43210\nEmail: info@newlifehospital.com",
		                 subFont);
		
		         PdfPCell infoCell = new PdfPCell();
		
		         infoCell.addElement(hospital);
		         infoCell.addElement(tagline);
		         infoCell.addElement(contact);
		
		         infoCell.setPaddingTop(10);
		
		         infoCell.setBorder(Rectangle.NO_BORDER);
		
		         headerTable.addCell(infoCell);
		
		         document.add(headerTable);
		            // =====================================================
		            // 💊 TITLE
		            // =====================================================
		
		            Paragraph title = new Paragraph("PRESCRIPTION", headingFont);
		
		            title.setAlignment(Element.ALIGN_CENTER);
		            title.setSpacingAfter(20);
		
		            document.add(title);
		
		            // =====================================================
		            // 📌 PATIENT INFO TABLE
		            // =====================================================
		
		            PdfPTable infoTable = new PdfPTable(2);
		
		            infoTable.setWidthPercentage(100);
		            infoTable.setSpacingAfter(20);
		
		            // 🔥 ROWS
		
		            addStyledCell(infoTable, "Patient Name", labelFont, lightGray);
		            addStyledCell(infoTable, p.getPatientName(), valueFont, BaseColor.WHITE);
		
		            addStyledCell(infoTable, "Doctor", labelFont, lightGray);
		            addStyledCell(infoTable, "Dr. " + p.getDoctorName(), valueFont, BaseColor.WHITE);
		
		            addStyledCell(infoTable, "Date", labelFont, lightGray);
		            addStyledCell(infoTable, p.getAppointmentDate(), valueFont, BaseColor.WHITE);
		
		            document.add(infoTable);
		
		            // =====================================================
		            // 💊 MEDICINE SECTION
		            // =====================================================
		
		            Paragraph medHeading = new Paragraph("MEDICINES", headingFont);
		            medHeading.setSpacingAfter(10);
		
		            document.add(medHeading);
		
		            PdfPTable medTable = new PdfPTable(4);
		
		            medTable.setWidthPercentage(100);
		            medTable.setSpacingAfter(20);
		
		            // 🔥 TABLE HEADERS
		
		            addStyledCell(medTable, "Sr No", labelFont, primaryBlue);
		            addStyledCell(medTable, "Medicine Name", labelFont, primaryBlue);
		            addStyledCell(medTable, "Dosage", labelFont, primaryBlue);
		            addStyledCell(medTable, "Timing", labelFont, primaryBlue);
		
		            // 🔥 DATA ROW
		
					/*
					 * addStyledCell(medTable, "1", valueFont, BaseColor.WHITE);
					 * addStyledCell(medTable, p.getMedicines(), valueFont, BaseColor.WHITE);
					 * addStyledCell(medTable, "1 Tablet", valueFont, BaseColor.WHITE);
					 * addStyledCell(medTable, "Morning", valueFont, BaseColor.WHITE);
					 */
		            
		            String[] medicineRows = p.getMedicines().split("\n");
		
		            int count = 1;
		
		            for(String row : medicineRows) {
		
		                String[] data = row.split("-");
		
		                addStyledCell(
		                        medTable,
		                        String.valueOf(count),
		                        valueFont,
		                        BaseColor.WHITE);
		
		                addStyledCell(
		                        medTable,
		                        data.length > 0 ? data[0].trim() : "",
		                        valueFont,
		                        BaseColor.WHITE);
		
		                addStyledCell(
		                        medTable,
		                        data.length > 1 ? data[1].trim() : "",
		                        valueFont,
		                        BaseColor.WHITE);
		
		                addStyledCell(
		                        medTable,
		                        data.length > 2 ? data[2].trim() : "",
		                        valueFont,
		                        BaseColor.WHITE);
		
		                count++;
		            }
		            document.add(medTable);
		
		            // =====================================================
		            // 📝 NOTES
		            // =====================================================
		
		            Paragraph noteHeading = new Paragraph("NOTES", headingFont);
		
		            noteHeading.setSpacingAfter(10);
		
		            document.add(noteHeading);
		
		            PdfPTable noteTable = new PdfPTable(1);
		
		            noteTable.setWidthPercentage(100);
		
		            PdfPCell noteCell = new PdfPCell(new Phrase(p.getNotes(), valueFont));
		
		            noteCell.setPadding(15);
		            noteCell.setFixedHeight(50);
		
		            noteTable.addCell(noteCell);
		
		            document.add(noteTable);
		
		            // =====================================================
		            // ✍ SIGNATURE
		            // =====================================================
		
		            Paragraph signHeading = new Paragraph("DOCTOR SIGNATURE", headingFont);
		
		            signHeading.setSpacingBefore(25);
		            signHeading.setSpacingAfter(10);
		
		            document.add(signHeading);
					/*
					 * Paragraph sign = new Paragraph(
					 * "__________________________\nDr. Vaidik\nMBBS, MD (Physician)", valueFont);
					 * 
					 * sign.setSpacingAfter(30);
					 * 
					 * document.add(sign);
					 */
		            
		            
					/*
					 * String signPath = getServletContext() .getRealPath("/images/V2.png");
					 * 
					 * Image signImg = Image.getInstance(signPath);
					 * 
					 * signImg.scaleToFit(120, 50);
					 * 
					 * document.add(signImg);
					 */
		            
		            try {
		
		                String doctorName = p.getDoctorName()
		                        .toLowerCase()
		                        .replace(" ", "");
		
		                String signPath = request.getServletContext()
		                        .getResource("/images/signatures/" + doctorName + ".png")
		                        .getPath();
		
		                System.out.println(signPath);
		
		                Image signImg = Image.getInstance(signPath);
		
		                signImg.scaleToFit(120, 50);
		
		                document.add(signImg);
		
		            } catch (Exception e) {
		
		                e.printStackTrace();
		
		                Paragraph noSign = new Paragraph(
		                        "Digital Signature Not Available",
		                        valueFont);
		
		                document.add(noSign);
		            }
		
					/*
					 * Paragraph doctorInfo = new Paragraph( "Dr. Vaidik\nMBBS, MD (Physician)",
					 * valueFont);
					 */
		            Paragraph doctorInfo = new Paragraph(
		            	    "Dr. " + p.getDoctorName(),
		            	    valueFont);
		            doctorInfo.setSpacingAfter(20);
		
		            document.add(doctorInfo);
		            
		            
		            
		
		            // =====================================================
		            // ❤️ FOOTER
		            // =====================================================
		
					/*
					 * Paragraph footer = new Paragraph(
					 * "Get Well Soon!\nThank you for trusting NewLife Hospital.", footerFont);
					 * 
					 * footer.setAlignment(Element.ALIGN_CENTER); footer.setSpacingBefore(30);
					 * 
					 * document.add(footer);
					 */
		         // =====================================================
		         // ❤️ FOOTER BAR
		         // =====================================================
		
		         PdfPTable footerTable = new PdfPTable(1);
		
		         footerTable.setWidthPercentage(100);
		
		         PdfPCell footerCell = new PdfPCell(
		                 new Phrase(
		                		 "24x7 Emergency Support | +91 98765 43210 | www.newlifehospital.com",                         new Font(
		                                 Font.FontFamily.HELVETICA,
		                                 11,
		                                 Font.BOLD,
		                                 BaseColor.WHITE)));
		
		         footerCell.setBackgroundColor(primaryBlue);
		
		         footerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		
		         footerCell.setPadding(12);
		
		         footerCell.setBorder(Rectangle.NO_BORDER);
		
		         footerTable.addCell(footerCell);
		
		         footerTable.setSpacingBefore(20);
		
		         document.add(footerTable);
		            document.close();
		
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    private void viewPrescription(
		            HttpServletRequest request,
		            HttpServletResponse response)
		            throws ServletException, IOException {

		        try {

		            int id = Integer.parseInt(
		                    request.getParameter("id"));

		            DButil dbUtil = new DButil();

		            PrescriptionDAO dao =
		                    new PrescriptionDAO(dbUtil);

		            PrescriptionDTO p =
		                    dao.getPrescriptionByAppointmentId(id);
		            
		            System.out.println("Prescription = " + p);
		            request.setAttribute("prescription", p);

		            RequestDispatcher rd =
		                    request.getRequestDispatcher(
		                            "doctor/ViewPrescription.jsp");

		            rd.forward(request, response);

		        } catch (Exception e) {

		            e.printStackTrace();
		        }
		    }
		    // =====================================================
		    // 🔥 REUSABLE CELL METHOD
		    // =====================================================
		
		    private void addStyledCell(
		            PdfPTable table,
		            String text,
		            Font font,
		            BaseColor bgColor) {
		
		        PdfPCell cell = new PdfPCell(new Phrase(text, font));
		
		        cell.setPadding(10);
		        cell.setBackgroundColor(bgColor);
		
		        if (bgColor.equals(new BaseColor(10, 79, 175))) {
		            cell.setPhrase(new Phrase(text,
		                    new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE)));
		        }
		
		        table.addCell(cell);
		    }
		}