package com.iiproject.donationpj.controller;

import com.iiproject.donationpj.entity.UserDonation;
import com.iiproject.donationpj.dtos.UserDonationDto;
import com.iiproject.donationpj.helper.Utils;
import com.iiproject.donationpj.service.DonationService;
import com.iiproject.donationpj.service.UserDonationService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user-donations")
public class UserDonationController {

    private UserDonationService userDonationService;

    private DonationService donationService;

    @Autowired
    public UserDonationController(UserDonationService userDonationService, DonationService donationService) {
        this.userDonationService = userDonationService;
        this.donationService = donationService;
    }

    //    ADD NEW USER DONATIONS
// userDonationStatus:   0: Cho Xac Nhan - Approve, 1: Da Xac Nhan - Approved
    @PostMapping("/save")
    public @ResponseBody ResponseEntity<Boolean> saveDonation(@RequestBody @Valid UserDonationDto theUserDonationDto) {
        UserDonation theUserDonation = new UserDonation();
        theUserDonation.setUserDonationName(theUserDonationDto.getUserDonationName());
        theUserDonation.setUserDonationMoney(Integer.parseInt(theUserDonationDto.getUserDonationMoney()));
        theUserDonation.setUserDonationText(theUserDonationDto.getUserDonationText());
        theUserDonation.setUserDonationStatus(0);
        theUserDonation.setCreated(Utils.getDateTime());
        theUserDonation.setDonation(donationService.findByID(theUserDonationDto.getDonationId()));
        theUserDonation.getDonation().getUserDonationList().add(theUserDonation);
        userDonationService.save(theUserDonation);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    //  DETAIL
    @GetMapping("/detail")
    public String detail(@RequestParam("userDonationId") int theId, Model theModel) {
        UserDonation userDonation = userDonationService.findByID(theId);
        theModel.addAttribute("userDonation", userDonation);
        return "admin/donationEntity/donation-detail-form";
    }

    //    CHANGE USER DONANTION STATUS
    @PostMapping("/updateStatus")
    public @ResponseBody ResponseEntity<Integer> updateStatus(@RequestBody UserDonationDto theUserDonationDto) {
        UserDonation theUserDonation = userDonationService.findByID(theUserDonationDto.getId());
        if (theUserDonationDto.getUserDonationStatus() == 1) {
            theUserDonation.setUserDonationStatus(1);
        } else {
            theUserDonation.setUserDonationStatus(0);
        }
        userDonationService.save(theUserDonation);
        return ResponseEntity.ok(theUserDonation.getDonation().getMoney());
    }
}
