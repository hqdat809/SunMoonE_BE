package com.example.ChamSocDinhDuong.controller;

import com.example.ChamSocDinhDuong.model.UserBank;
import com.example.ChamSocDinhDuong.service.UserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-bank")
public class UserBankController {

    @Autowired
    private final UserBankService userBankService;

    public UserBankController(UserBankService userBankService) {
        this.userBankService = userBankService;
    }


    // Create or Update Bank for a User
    @PostMapping("/{userId}")
    public ResponseEntity<UserBank> createOrUpdateBank(
            @PathVariable String userId,
            @RequestBody UserBank bankRequest
    ) {
        UserBank savedBank = userBankService.saveOrUpdateBank(userId, bankRequest);
        return ResponseEntity.ok(savedBank);
    }
}
