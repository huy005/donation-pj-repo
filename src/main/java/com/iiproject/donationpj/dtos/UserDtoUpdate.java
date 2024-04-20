package com.iiproject.donationpj.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoUpdate {
    private int id;

    @NotBlank(message = "Enter full name!!!")
    private String fullName;

    @NotBlank(message = "Enter address!!!")
    private String address;

    @NotBlank(message = "Enter the phone number!!!")
    @Size(min = 10, max= 14 , message = "Phone number contains digits from 0-9 with 10-14 digits!!!")
    @Pattern(regexp = "^\\d+$", message = "Enter digits only!!!")
    private String phoneNumber;

    private String role;
}
