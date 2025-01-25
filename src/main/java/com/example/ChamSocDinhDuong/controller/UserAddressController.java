package com.example.ChamSocDinhDuong.controller;

import com.example.ChamSocDinhDuong.model.UserAddress;
import com.example.ChamSocDinhDuong.model.UserBank;
import com.example.ChamSocDinhDuong.service.UserAddressService;
import com.example.ChamSocDinhDuong.service.UserBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user-address")
public class UserAddressController {

    @Autowired
    private final UserAddressService userAddressService;

    public UserAddressController(UserAddressService userAddressService) {
        this.userAddressService = userAddressService;
    }


    // Create or Update Bank for a User
    @PostMapping("/{userId}")
    public ResponseEntity<UserAddress> createOrUpdateAddress(
            @PathVariable String userId,
            @RequestBody UserAddress addressRequest
    ) {
        UserAddress savedAddress = userAddressService.saveOrUpdateAddress(userId, addressRequest);
        return ResponseEntity.ok(savedAddress);
    }

    // Create or Update Bank for a User
    @GetMapping("/{userId}")
    public ResponseEntity<UserAddress> getUserAddress(
            @PathVariable String userId
    ) {
        UserAddress address = userAddressService.getUserAddress(userId);
        return ResponseEntity.ok(address);
    }
}
