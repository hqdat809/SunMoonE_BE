package com.example.ChamSocDinhDuong.service;

import com.example.ChamSocDinhDuong.DTO.UserDTO;
import com.example.ChamSocDinhDuong.auth.AuthenticationRequest;
import com.example.ChamSocDinhDuong.auth.AuthenticationResponse;
import com.example.ChamSocDinhDuong.config.JwtService;
import com.example.ChamSocDinhDuong.model.Role;
import com.example.ChamSocDinhDuong.model.User;
import com.example.ChamSocDinhDuong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserDTO userDTO) {

        SecureRandom secureRandom = new SecureRandom();
        int otp = 100000 + secureRandom.nextInt(900000);

        var user = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .role(Role.USER)
                .customerId(userDTO.getCustomerId())
                .isVerified(false)
                .verifyCode(otp)
                .build();

            userRepository.save(user);

//        emailService.sendSimpleEmail(userDTO.getEmail(), "Xác thực email SunMoonE.vn", "Mã xác thực email của bạn là: " + user.getVerifyCode());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).userDetails(user).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).userDetails(user).build();
    }
}
