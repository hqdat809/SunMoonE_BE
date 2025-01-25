package com.example.ChamSocDinhDuong.service;

import com.example.ChamSocDinhDuong.model.User;
import com.example.ChamSocDinhDuong.model.UserBank;
import com.example.ChamSocDinhDuong.repository.UserBankRepository;
import com.example.ChamSocDinhDuong.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserBankService {
    @Autowired
    private UserBankRepository userBankRepository;

    @Autowired
    private UserRepository userRepository;

    // Create or Update Bank
    @Transactional
    public UserBank saveOrUpdateBank(String userId, UserBank bankRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        UserBank bank = userBankRepository.findByUserId(userId)
                .orElse(new UserBank());

        // Update bank fields
        bank.setBankName(bankRequest.getBankName());
        bank.setBankId(bankRequest.getBankId());
        bank.setFullName(bankRequest.getFullName());
        bank.setUser(user);

        return userBankRepository.save(bank);
    }
}
