package com.iiproject.donationpj.service;

import com.iiproject.donationpj.entity.Donation;
import com.iiproject.donationpj.entity.UserDonation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DonationService {

    List<Donation> findAll();
    Page<Donation> findAll(Pageable pageable);

//    Page<Donation> findByIdContaining(int theDonationId, Pageable pageable);

    void save(Donation theDonation);

    Page<Donation> findByKeywords(String keyword, int statusNumber, Pageable pageable);

    void deleteById(int theId);

    Donation findByID(int theId);
//
//    void saveAndUpdate(Donation theUser);

}
