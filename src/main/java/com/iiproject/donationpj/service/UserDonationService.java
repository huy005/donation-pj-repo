package com.iiproject.donationpj.service;

import com.iiproject.donationpj.entity.UserDonation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserDonationService {
    void save(UserDonation theUserDonation);
    UserDonation findByID(int theId);
    Page<UserDonation> findByIdContaining(int theDonationId,Pageable pageable);
    Page<UserDonation> findAll(Pageable pageable);
}
