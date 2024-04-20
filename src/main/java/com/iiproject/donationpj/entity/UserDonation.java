package com.iiproject.donationpj.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_donation_db")
public class UserDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_donation_name")
    private String userDonationName;

    @Column(name="money")
    private int userDonationMoney;

    @Column(name="user_donation_status")
    private int userDonationStatus;

    @Column(name="user_donation_text")
    private  String userDonationText;

    @Column(name="created")
    private String created;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="donation_id")
    private Donation donation;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;
}
