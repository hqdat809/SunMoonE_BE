package com.example.ChamSocDinhDuong.service;

import com.example.ChamSocDinhDuong.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final JwtService jwtService;
}
