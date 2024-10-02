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
@Table(name = "_bank")
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String bankId;
    String fullName;

    public Bank(String name, String bankId, String fullName) {
        this.name = name;
        this.bankId = bankId;
        this.fullName = fullName;
    }
}
