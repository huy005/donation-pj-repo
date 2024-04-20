package com.iiproject.donationpj.controller;

import com.iiproject.donationpj.entity.Donation;
import com.iiproject.donationpj.helper.PaginationHelper;
import com.iiproject.donationpj.service.DonationService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/")
public class PublicController {


    private DonationService donationService;
    @Autowired
    public PublicController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping("home")
    public String showHome(Model theModel, @PageableDefault(page = 0, size = 5) Pageable pageable){
        Page<Donation> donationPage = donationService.findAll(pageable);
        theModel.addAttribute("donationPage", PaginationHelper.createPagination(donationPage));
        theModel.addAttribute("donations", donationPage.getContent());
        return "public/home";
    }

    //  DETAIL ?donationId={theId}
    @GetMapping("/detail/{theId}")
    public String detail(@PathVariable String theId, Model theModel) {
        int theIdInt = Integer.parseInt(theId);
        Donation donation = donationService.findByID(theIdInt);
        theModel.addAttribute("donation", donation);
        return "public/detail";
    }
}
