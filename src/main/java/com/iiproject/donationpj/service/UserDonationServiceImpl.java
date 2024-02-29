package com.iiproject.donationpj.service;

import com.iiproject.donationpj.entity.Donation;
import com.iiproject.donationpj.entity.User;
import com.iiproject.donationpj.entity.UserDonation;
import com.iiproject.donationpj.repository.DonationRepository;
import com.iiproject.donationpj.repository.UserDonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserDonationServiceImpl implements UserDonationService {

    private UserDonationRepository userDonationRepository;
    private DonationRepository donationRepository;
    @Autowired
    public UserDonationServiceImpl(UserDonationRepository userDonationRepository, DonationRepository donationRepository) {
        this.userDonationRepository = userDonationRepository;
        this.donationRepository = donationRepository;
    }

    @Override
    public void save(UserDonation theUserDonation) {
        userDonationRepository.save(theUserDonation);
    }

    @Override
    public UserDonation findByID(int theId) {
        Optional<UserDonation> userDonationResult = userDonationRepository.findById(theId);
        UserDonation theUserDonation = null;
        if (userDonationResult.isPresent()) {
            theUserDonation = userDonationResult.get();
        } else {
            throw new RuntimeException("Did not find user id - " + theId);
        }
        Donation theDonation = theUserDonation.getDonation();
        int sum = 0;
        if(theUserDonation.getUserDonationStatus() == 0){
                sum = theUserDonation.getUserDonationMoney();
            }
        theDonation.setMoney(sum + theDonation.getMoney());
        return theUserDonation;
    }

    @Override
    public Page<UserDonation> findByIdContaining(int theDonationId, Pageable pageable) {
        return userDonationRepository.findByIdContaining(theDonationId, pageable);
    }

    @Override
    public Page<UserDonation> findAll(Pageable pageable) {
        return userDonationRepository.findAll(pageable);
    }
}
