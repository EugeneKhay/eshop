package com.eshop.controller;

import com.eshop.domain.Client;
import com.eshop.domain.Order;
import com.eshop.service.OrderService;
import com.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.*;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.logging.Logger;

@Controller
@RequestMapping
public class OrderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private JavaMailSender mailSender;

    private Logger logger = Logger.getLogger("logger");


    @PostMapping("/confirm")
    public String confirmOrder(HttpSession session,
                               @RequestParam(name = "paymentMethod") String paymentMethod,
                               @RequestParam(name = "deliveryMethod") String deliveryMethod,
                               @RequestParam(name = "deliveryAddress", required = false) String deliveryAddress,
                               Model model) throws MessagingException {
        Client client = (Client) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<Order> orders = client.getOrders();
        orders.add(orderService.makeNewOrder(session, paymentMethod, deliveryMethod, deliveryAddress));
        model.addAttribute("client", client);

//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("4358514@gmail.com");
//        message.setTo("seelenrauf@mail.ru");
//        message.setSubject("New order");
//        message.setText("Congratulations!");
//        mailSender.send(message);

//        Properties prop = new Properties();
//        prop.put("mail.smtp.auth", true);
//        prop.put("mail.smtp.starttls.enable", "true");
//        prop.put("mail.smtp.host", "smtp.yandex.ru");
//        prop.put("mail.smtp.port", "25");
//
//        Session sessionMail = Session.getInstance(prop, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("eshopivanov", "qwerty007");
//            }
//        });
//
//        Message message = new MimeMessage(sessionMail);
//        message.setFrom(new InternetAddress("eshopivanov@yandex.ru"));
//        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("seelenrauf@mail.ru"));
//        message.setSubject("Mail Subject");
//
//        String msg = "This is my first email using JavaMailer";
//
//        MimeBodyPart mimeBodyPart = new MimeBodyPart();
//        mimeBodyPart.setContent(msg, "text/html");
//
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(mimeBodyPart);
//
//        message.setContent(multipart);
//
//        Transport.send(message);



        logger.info("The order created and saved to DB");
        return "personal";
    }

    @PostMapping("/editorder")
    public String editOrderByAdmin(@RequestParam(name = "orderForEdit") int id,
                                   @RequestParam(name = "paymentStatus") String paymentStatus,
                                   @RequestParam(name = "orderStatus") String orderStatus,
                                   Model model) {
        orderService.editOrder(id, paymentStatus, orderStatus);
        model.addAttribute("orders", orderService.getAllOrders());
        logger.info("The order's parameteres changed by admin");
        return "adminpage";
    }
}
























