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
public class UserDonationDto {
    private int id;

    @NotBlank(message = "Enter full name!!!")
    @Size(max= 20 , message = "Full name contains 20 characters maximum!!!")
    private String userDonationName;

    @NotBlank(message = "Enter the amount of money!!!")
    @Pattern(regexp = "^\\d+$", message = "Enter digits only!!!")
    private String userDonationMoney;

    @NotBlank(message = "Enter the donation text!!!")
    private String userDonationText;

    private int userDonationStatus;

    private int donationId;
}
