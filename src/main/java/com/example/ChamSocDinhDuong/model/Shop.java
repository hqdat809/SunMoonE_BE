package com.example.ChamSocDinhDuong.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "_shop")
public class Shop{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String phoneNumber;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String address;
    @Column(nullable = false)
    String facebookAddress;
    @Column(nullable = false)
    String youtubeAddress;

    public Shop(String s, String number, String mail, String s1, String url, String url1) {
    }
}
