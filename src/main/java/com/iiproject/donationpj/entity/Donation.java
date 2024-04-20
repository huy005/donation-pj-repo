package com.iiproject.donationpj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="donation_db")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="donation_code")
    private String donationCode;

    @Column(name="donation_name")
    private String donationName;

    @Column(name="donation_description")
    private String donationDescription;

    @Column(name="money")
    private int money;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private  String endDate;

    @Column(name="organization_name")
    private String organizationName;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="created")
    private String created;

    @Column(name="donation_status")
    private int donationStatus;

    @OneToMany(mappedBy = "donation",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<UserDonation> userDonationList;
}
