package com.example.ChamSocDinhDuong.repository;

import com.example.ChamSocDinhDuong.model.Shop;
import com.example.ChamSocDinhDuong.model.User;
import com.example.ChamSocDinhDuong.model.UserAddress;
import com.example.ChamSocDinhDuong.model.UserBank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAddressRepository extends JpaRepository<UserAddress, String> {
    Optional<UserAddress> findByUserId(String userId);

}
