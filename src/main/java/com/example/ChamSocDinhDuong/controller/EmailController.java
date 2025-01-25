package com.example.ChamSocDinhDuong.controller;

import com.example.ChamSocDinhDuong.model.User;
import com.example.ChamSocDinhDuong.repository.UserRepository;
import com.example.ChamSocDinhDuong.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendSimpleEmail("hqdat0809@gmail.com", "Test Subject", "Test Body");
        return "Email sent successfully!";
    }


}
