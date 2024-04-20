package com.iiproject.donationpj.service;

import com.iiproject.donationpj.entity.Donation;
import com.iiproject.donationpj.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationServiceImpl implements DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    //    DONATION CONTROLLER
    @Override
    public Page<Donation> findAll(Pageable pageable) {
        return donationRepository.findAll(pageable);
    }

    @Override
    public void save(Donation theDonation) {
        donationRepository.save(theDonation);
    }

    @Override
    public Page<Donation> findByKeywords(String keyword, int statusNumber, Pageable pageable) {
        return donationRepository.findByKeywords(keyword, statusNumber, pageable);
    }

    @Override
    public void deleteById(int theId) {
        donationRepository.deleteById(theId);
    }

    @Override
    public Donation findByID(int theId) {
        Optional<Donation> donationResult = donationRepository.findById(theId);
        Donation theDonation = null;
        if (donationResult.isPresent()) {
            theDonation = donationResult.get();
        } else {
            throw new RuntimeException("Did not find donation id - " + theId);
        }
        return theDonation;
    }

    // PUBLIC CONTROLLER
    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }
}
