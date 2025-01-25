package com.example.ChamSocDinhDuong.controller;

import com.example.ChamSocDinhDuong.model.Role;
import com.example.ChamSocDinhDuong.model.User;
import com.example.ChamSocDinhDuong.repository.UserRepository;
import com.example.ChamSocDinhDuong.type.ChangePasswordRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {

        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User'id is not found "+ id)
        );

        return ResponseEntity.ok(user);
    }


    @PutMapping("/update-role/{id}")
    public ResponseEntity<User> updateRole(@PathVariable String id, @RequestParam("role") Role role) {

        System.out.println("+++++++++++++++++++" + role);

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User'id is not found "+ id)
        );

        user.setRole(role);

        return ResponseEntity.ok(userRepository.save(user));
    }

    @PutMapping("/update-customer-id/{id}")
    public ResponseEntity<User> updateCustomerId(@PathVariable String id, @RequestParam("customerId") Integer customerId) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User's id is not found "+ id)
        );

        user.setCustomerId(customerId);

        return ResponseEntity.ok(userRepository.save(user));
    }

    @PutMapping("/update-password/{id}")
    public ResponseEntity<User> updatePassword(@PathVariable String id, @RequestParam("password") String password) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User's id is not found "+ id)
        );

        String newPassword = passwordEncoder.encode(password);

        user.setPassword(newPassword);

        return ResponseEntity.ok(userRepository.save(user));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<?> changePassword(@PathVariable String id, @RequestBody ChangePasswordRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(request.getNewPassword()));
                userRepository.save(user);
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.badRequest().body("Invalid");
    }
}
