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
    String name;
    String phoneNumber;
    String email;
    String address;
    String facebookAddress;
    String youtubeAddress;

    public Shop(String s, String number, String mail, String s1, String url, String url1) {
    }
}
