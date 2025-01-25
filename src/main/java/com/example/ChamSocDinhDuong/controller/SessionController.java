package com.example.ChamSocDinhDuong.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session")
public class SessionController {
    @GetMapping("")
    public ResponseEntity<String> checkSession() {
        return ResponseEntity.ok("Ok");
    }
}
