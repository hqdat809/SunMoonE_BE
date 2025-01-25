package com.example.ChamSocDinhDuong.service;

import com.example.ChamSocDinhDuong.model.User;
import com.example.ChamSocDinhDuong.model.UserAddress;
import com.example.ChamSocDinhDuong.model.UserBank;
import com.example.ChamSocDinhDuong.repository.UserAddressRepository;
import com.example.ChamSocDinhDuong.repository.UserBankRepository;
import com.example.ChamSocDinhDuong.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAddressService {
    @Autowired
    private UserAddressRepository userAddressRepository;

    @Autowired
    private UserRepository userRepository;

    // Create or Update Bank
    @Transactional
    public UserAddress saveOrUpdateAddress(String userId, UserAddress addressRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        UserAddress address = userAddressRepository.findByUserId(userId)
                .orElse(new UserAddress());

        // Update bank fields
        address.setName(addressRequest.getName());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        address.setAddress(addressRequest.getAddress());
        address.setLocation(addressRequest.getLocation());
        address.setWardName(addressRequest.getWardName());
        address.setUser(user);

        return userAddressRepository.save(address);
    }

    public UserAddress getUserAddress(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        UserAddress address = userAddressRepository.findByUserId(userId)
                .orElse(new UserAddress());

        return address;
    }
}
