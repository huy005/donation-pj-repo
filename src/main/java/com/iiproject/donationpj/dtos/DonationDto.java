package com.iiproject.donationpj.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DonationDto {

    private int id;

    @NotBlank(message = "Enter donation code!!!")
    @Size(max= 5 , message = "Full name contains 5 characters maximum!!!")
    private String donationCode;

    @NotBlank(message = "Enter donation name!!!")
    private String donationName;

    @NotBlank(message = "Enter donation description!!!")
    private String donationDescription;

    @NotBlank(message = "Enter the start date!!!")
    private String startDate;

    @NotBlank(message = "Enter the end date!!!")
    private  String endDate;

    @NotBlank(message = "Enter organization name!!!")
    private String organizationName;

    @NotBlank(message = "Enter the phone number!!!")
    @Pattern(regexp = "^\\d+$", message = "Enter digits only!!!")
    @Size(min = 10, max= 14 , message = "Phone number contains digits from 0-9 with 10-14 digits!!!")
    private String phoneNumber;

    private int donationStatus;

}
