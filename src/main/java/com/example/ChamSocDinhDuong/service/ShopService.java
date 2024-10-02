package com.example.ChamSocDinhDuong.service;

import com.example.ChamSocDinhDuong.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final ShopRepository shopRepository;
}
