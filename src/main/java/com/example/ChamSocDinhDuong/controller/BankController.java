package com.example.ChamSocDinhDuong.controller;

import com.example.ChamSocDinhDuong.model.Bank;
import com.example.ChamSocDinhDuong.model.Shop;
import com.example.ChamSocDinhDuong.repository.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bank")
@RequiredArgsConstructor
public class BankController {
    private final BankRepository bankRepository;

    @GetMapping("")
    public ResponseEntity<List<Bank>> getBankInfo() {
        return ResponseEntity.ok(bankRepository.findAll());
    }

    @PostMapping("/{id}")
    public ResponseEntity<Bank> editBankInfo(@PathVariable String id, @RequestBody Bank bank) {


        Bank bankUpdate = bankRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bank'id is not found "+ id)
        );

        bankUpdate.setName(bank.getName());
        bankUpdate.setBankId(bank.getBankId());
        bankUpdate.setFullName(bank.getFullName());

        return ResponseEntity.ok(bankRepository.save(bankUpdate));
    }
}
