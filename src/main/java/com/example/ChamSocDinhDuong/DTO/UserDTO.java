package com.example.ChamSocDinhDuong.DTO;
import com.example.ChamSocDinhDuong.model.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO {

    private String id;

    @Email(message = "Email should be valid")
    @NotNull(message = "Email is required")
    private String email;

    @NotNull(message = "Password shouldn't be null!!")
    private String password;

    @NotNull(message = "First Name is required")
    private String firstName;

    @NotNull(message = "Last Name is required")
    private String lastName;

    @NotNull(message = "Phone Number shouldn't be null!!")
    private String phone;

    private int customerId;

    private boolean isVerified;

    private int verifyCode;
}
