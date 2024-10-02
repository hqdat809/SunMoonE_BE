package com.example.ChamSocDinhDuong.controller;

import com.example.ChamSocDinhDuong.auth.AuthenticationRequest;
import com.example.ChamSocDinhDuong.auth.AuthenticationResponse;
import com.example.ChamSocDinhDuong.auth.ResisterRequest;
import com.example.ChamSocDinhDuong.model.User;
import com.example.ChamSocDinhDuong.repository.UserRepository;
import com.example.ChamSocDinhDuong.service.AuthenticationService;
import com.example.ChamSocDinhDuong.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController 
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final EmailService emailService;
    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody ResisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/send-otp")
    public ResponseEntity<String> sendOTP(
            @RequestParam(value = "email", defaultValue = "", required = false) String userEmail

    ) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found with email: "+ userEmail)
        );
        emailService.sendSimpleEmail(userEmail, "Xác thực email SunMoonE.vn", "Mã xác thực email của bạn là: " + user.getVerifyCode());
        return ResponseEntity.ok("Đã gửi mã xác thực qua email "+userEmail+" thành công!!");
    }

    @GetMapping("/verify-email")
    public ResponseEntity<String> verifyOTP(
            @RequestParam(value = "email", defaultValue = "", required = false) String userEmail,
            @RequestParam(value = "otp", defaultValue = "", required = false) String otp

    ) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy người dùng với email: "+ userEmail)
        );

        if (user.getVerifyCode() == Integer.parseInt(otp)) {
            user.setVerified(true);
            userRepository.save(user);
            return ResponseEntity.ok("Đã xác thực email thành công!!");
        }

        return new ResponseEntity<>("Mã xác thực không đúng, hãy nhập lại!!!", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/verify-phone-number")
    public ResponseEntity<String> verifyPhoneNumber(
            @RequestParam(value = "email", defaultValue = "", required = false) String userEmail,
            @RequestParam(value = "phoneNumber", defaultValue = "", required = false) String phoneNumber

    ) {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User is not found with email: "+ userEmail)
        );

        Optional<User> userByPhone = userRepository.findByPhoneNumber(phoneNumber);

        if (!user.isVerified()) {
            return new ResponseEntity<>(
                    "Người dùng chưa xác thực email, vui lòng xác thực email để thiết lập số điện thoại",
                    HttpStatus.BAD_REQUEST
            );
        }

        if (userByPhone.isPresent()) {
            return new ResponseEntity<>(
                    "Số điện thoại này đã được sử dụng, vui lòng sử dụng sđt khác",
                    HttpStatus.BAD_REQUEST
            );
        }

        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
        return ResponseEntity.ok("Đã xác thực thiết lập số điện thoại thành công!!");
    }
}
