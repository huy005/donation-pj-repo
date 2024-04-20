package com.iiproject.donationpj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@ToString(exclude = "userDonationList")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user_db")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="full_name")
    private String fullName;

    @Column(name="user_name")
    private String userName;

    @Column(name="address")
    private String address;

    @Column(name="email")
    private  String email;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="user_password")
    private String userPassword;

    @Column(name="user_status")
    private int userStatus;

    @Column(name="note")
    private String note;

    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="role_id")
    private Role role;

    @OneToMany(mappedBy = "user",
            cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH, CascadeType.REFRESH})
    private List<UserDonation> userDonationList;

}
