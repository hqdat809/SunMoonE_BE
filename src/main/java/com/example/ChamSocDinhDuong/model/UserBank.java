package com.example.ChamSocDinhDuong.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@Table(name = "user_bank")
public class UserBank {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    @Column(nullable = false)
    String bankName;
    @Column(nullable = false)
    String bankId;
    @Column(nullable = false)
    String fullName;
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public UserBank(String bankName, String bankId, String fullName) {
        this.bankName = bankName;
        this.bankId = bankId;
        this.fullName = fullName;
    }
}
