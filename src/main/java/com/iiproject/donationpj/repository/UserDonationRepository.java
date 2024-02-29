package com.iiproject.donationpj.repository;

import com.iiproject.donationpj.entity.Donation;
import com.iiproject.donationpj.entity.UserDonation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserDonationRepository extends JpaRepository<UserDonation, Integer> {
    @Query("select ud from UserDonation ud where ud.donation.id = :theDonationId" )
    Page<UserDonation> findByIdContaining(@Param("theDonationId")int TheDonationId, Pageable pageable);

    Page<UserDonation> findAll(Pageable pageable);
}
