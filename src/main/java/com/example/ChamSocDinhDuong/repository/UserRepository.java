package com.example.ChamSocDinhDuong.repository;

import com.example.ChamSocDinhDuong.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPhone(String phoneNumber);

}
