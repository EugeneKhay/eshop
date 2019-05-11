//package com.eshop.sender;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//public class EmailSender {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    public void sendEmail() {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("4358514@gmail.com");
//        message.setTo("seelenrauf@mail.ru");
//        message.setSubject("New order");
//        message.setText("Congratulations!");
//        mailSender.send(message);
//    }
//}
