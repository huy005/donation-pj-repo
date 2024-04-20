package com.iiproject.donationpj.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int id;

    @NotBlank(message = "Enter full name!!!")
    private String fullName;

    @NotBlank(message = "Enter account!!!")
    @Size(min = 5, max= 15 , message = "Account contains 5-15 characters!!!")
    private String userName;

    @NotBlank(message = "Enter address!!!")
    private String address;
    @NotBlank(message = "Enter the email!!!")
    @Pattern(regexp = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$", message = "Email has to match with the format: abc@xyz.oiu!!!")
    private  String email;

    @NotBlank(message = "Enter the phone number!!!")
    @Size(min = 10, max= 14 , message = "Phone number contains digits from 0-9 with 10-14 digits!!!")
    @Pattern(regexp = "^\\d+$", message = "Enter digits only!!!")
    private String phoneNumber;

    @NotBlank(message = "Enter password!!!")
    @Size(min = 6, max= 12 , message = "Password contains 6-12 characters!!!")
    private String userPassword;

    private int userStatus;

    private String note;

    private String role;
}
