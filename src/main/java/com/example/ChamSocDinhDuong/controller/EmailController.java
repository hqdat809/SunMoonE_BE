package com.example.ChamSocDinhDuong.controller;

import com.example.ChamSocDinhDuong.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendSimpleEmail("hqdat0809@gmail.com", "Test Subject", "Test Body");
        return "Email sent successfully!";
    }
}
