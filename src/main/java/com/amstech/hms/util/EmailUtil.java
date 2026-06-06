package com.amstech.hms.util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import java.io.UnsupportedEncodingException;
public class EmailUtil {

    public static void sendOTP(String toEmail, String otp)  throws UnsupportedEncodingException{

        final String fromEmail = "jaydeepbhai07@gmail.com";
        final String appPassword = "bposccydjpqqyulz";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, appPassword);
                }
            });

        try {
            Message message = new MimeMessage(session);
        //    message.setFrom(new InternetAddress(fromEmail));
            message.setFrom(new InternetAddress(fromEmail, "NewLife Hospital"));     
            message.setSubject("🔐 NewLife Hospital - Password Reset OTP");

            String content = 
            "Dear User,\n\n" +
            "Your OTP for NewLife Hospital is: " + otp + "\n\n" +
            "Valid for 5 minutes.\n\n" +
            "Regards,\nNewLife Hospital Team";

            message.setText(content);
            
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));

           // message.setSubject("Password Reset OTP");
            message.setSubject("🔐 NewLife Hospital - Password Reset OTP");
            message.setText("Your OTP is: " + otp + "\nValid for 5 minutes.");

            Transport.send(message);

            System.out.println("✅ Email sent successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}