package com.iiproject.donationpj.controller;


import com.iiproject.donationpj.entity.Donation;
import com.iiproject.donationpj.dtos.DonationDto;
import com.iiproject.donationpj.entity.UserDonation;
import com.iiproject.donationpj.helper.PaginationHelper;
import com.iiproject.donationpj.helper.Utils;
import com.iiproject.donationpj.service.DonationService;
import com.iiproject.donationpj.service.UserDonationService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/donations")
public class DonationController {

    HttpSession httpSession;

    private DonationService donationService;
    private UserDonationService userDonationService;

    @Autowired
    public DonationController(DonationService donationService, UserDonationService userDonationService, HttpSession httpSession) {
        this.donationService = donationService;
        this.userDonationService = userDonationService;
        this.httpSession = httpSession;
    }

    @Autowired
    MessageSource msg;

    //    LIST DONATIONS
    @GetMapping("/list")
    public String listDonations(Model theModel, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<Donation> donationPage = donationService.findAll(pageable);
        theModel.addAttribute("donationPage", PaginationHelper.createPagination(donationPage));
        theModel.addAttribute("donations", donationPage.getContent());
        return "admin/donationEntity/donation-list";
    }

    //    ADD NEW DONATIONS
    @PostMapping("/save")
    public @ResponseBody ResponseEntity<Boolean> saveDonation(@RequestBody @Valid DonationDto theDonationDto) {
        Donation theDonation = new Donation();
        theDonation.setDonationCode(theDonationDto.getDonationCode());
        theDonation.setDonationName(theDonationDto.getDonationName());
        theDonation.setStartDate(theDonationDto.getStartDate());
        theDonation.setEndDate(theDonationDto.getEndDate());
        theDonation.setOrganizationName(theDonationDto.getOrganizationName());
        theDonation.setPhoneNumber(theDonationDto.getPhoneNumber());
        theDonation.setDonationDescription(theDonationDto.getDonationDescription());
        theDonation.setCreated(Utils.getDateTime());
        donationService.save(theDonation);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    //    SEARCH WITH PAGINATION
    // Khi click vao search
    // keyword2 để nhận biết có phải là nút search hay không
    // khi lùi phân trang mà bị mất keyword có nghĩa là phải phân biệt nút search và nút phân trang
//    Created- moi tao, Progress - Dang Quyen Gop, Ended - Ket thuc quyen gop, Closed - Dong Quyen Gop
    @GetMapping("/search")
    public String seachByKeywords(@RequestParam(required = false, name = "keyword") String keyword, @RequestParam(required = false, name = "keyword2") String keyword2, Integer statusNumber, @PageableDefault(page = 0, size = 5) Pageable pageable, Model model) {
        if (keyword2 != null) {
            httpSession.setAttribute("keyword", keyword);
        } else {
            keyword = (String) httpSession.getAttribute("keyword");
        }

        if (keyword.compareToIgnoreCase("Created") == 0) {
            statusNumber = 0;
        } else if (keyword.compareToIgnoreCase("Progress") == 0) {
            statusNumber = 1;
        } else if (keyword.compareToIgnoreCase("Ended") == 0) {
            statusNumber = 2;
        } else if (keyword.compareToIgnoreCase("Closed") == 0) {
            statusNumber = 3;
        } else {
            statusNumber = -1;
        }

        Page<Donation> pageSearched = null;
        if (!(StringUtils.isEmpty(keyword))) {
            pageSearched = donationService.findByKeywords(keyword, statusNumber, pageable);

        } else {
            pageSearched = donationService.findAll(pageable);
        }
        List<Donation> listSearched = pageSearched.getContent();

        if (CollectionUtils.isEmpty(listSearched)) {
            String emptyMessage = msg.getMessage("donations.list.empty", null, Locale.JAPAN);
            model.addAttribute("emptyMessage", emptyMessage);
        }
        model.addAttribute("pageSearched", PaginationHelper.createPagination(pageSearched));
        model.addAttribute("donations", listSearched);
        return "admin/donationEntity/donation-search";
    }

    //    DELETE
    @GetMapping("/delete")
    public String delete(@RequestParam("donationId") int theId) {
        donationService.deleteById(theId);
        return "redirect:/donations/list";
    }

    //    UPDATE
    @GetMapping("/update")
    public String update(@RequestParam("donationId") int theId, Model theModel) {
        Donation donation = donationService.findByID(theId);
        theModel.addAttribute("donation", donation);
        return "admin/donationEntity/donation-update-form";
    }

    //  SAVE AND UPDATE
    @PostMapping("/saveAndUpdate")
    public @ResponseBody ResponseEntity<Boolean> saveAndUpdate(@RequestBody @Valid DonationDto theDonationDto) {
        Donation theDonation = donationService.findByID(theDonationDto.getId());
        theDonation.setDonationCode(theDonationDto.getDonationCode());
        theDonation.setDonationName(theDonationDto.getDonationName());
        theDonation.setStartDate(theDonationDto.getStartDate());
        theDonation.setEndDate(theDonationDto.getEndDate());
        theDonation.setOrganizationName(theDonationDto.getOrganizationName());
        theDonation.setPhoneNumber(theDonationDto.getPhoneNumber());
        theDonation.setDonationDescription(theDonationDto.getDonationDescription());
        donationService.save(theDonation);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    //  DETAIL
    @GetMapping("/detail")
    public String detail(@RequestParam("donationId") int theId, Model theModel, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Donation donation = donationService.findByID(theId);
        theModel.addAttribute("donation", donation);

        Page<UserDonation> userDonationPage = userDonationService.findByIdContaining(theId, pageable);

        theModel.addAttribute("userDonationPage", PaginationHelper.createPagination(userDonationPage));
        theModel.addAttribute("userDonations", userDonationPage.getContent());
        return "admin/donationEntity/donation-detail-form";
    }


    //    CHANGE STATUS
    @PostMapping("/updateStatus")
    public @ResponseBody ResponseEntity<Boolean> updateStatus(@RequestBody DonationDto theDonationDto) {
        Donation theDonation = donationService.findByID(theDonationDto.getId());
        if (theDonationDto.getDonationStatus() == 1) {
            theDonation.setDonationStatus(1);
        } else if (theDonationDto.getDonationStatus() == 2) {
            theDonation.setDonationStatus(2);
        } else {
            theDonation.setDonationStatus(3);
        }
        donationService.save(theDonation);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}

