package com.iiproject.donationpj.repository;

import com.iiproject.donationpj.entity.Donation;
import com.iiproject.donationpj.entity.UserDonation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Integer> {

    Page<Donation> findAll(Pageable pageable);

    @Query("select d from Donation d where d.donationCode like %:keyword% " +
            "or d.organizationName like %:keyword% " +
            "or d.phoneNumber like %:keyword% " +
            "or d.donationStatus =:statusNumber " +
            "order by d.id asc")
   Page<Donation> findByKeywords(@Param("keyword") String keyword,
                                 @Param("statusNumber") int statusNumber,
                                 Pageable pageable);
}
