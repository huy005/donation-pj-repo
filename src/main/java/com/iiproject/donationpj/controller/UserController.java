package com.iiproject.donationpj.controller;


import com.iiproject.donationpj.entity.Role;
import com.iiproject.donationpj.entity.User;
import com.iiproject.donationpj.dtos.UserDto;
import com.iiproject.donationpj.dtos.UserDtoUpdate;
import com.iiproject.donationpj.helper.PaginationHelper;
import com.iiproject.donationpj.service.RoleService;
import com.iiproject.donationpj.service.UserService;
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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/users")
public class UserController {

    HttpSession httpSession;

    private UserService userService;

    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService, HttpSession httpSession) {
        this.userService = userService;
        this.roleService = roleService;
        this.httpSession = httpSession;
    }

    @Autowired
    MessageSource msg;

    //    LIST USERS
    @GetMapping("/list")
    public String listUsers(Model theModel, @PageableDefault(page = 0, size = 5) Pageable pageable) {
        Page<User> userPage = userService.findAll(pageable);
        theModel.addAttribute("userPage", PaginationHelper.createPagination(userPage));
        theModel.addAttribute("users", userPage.getContent());
        return "admin/userEntity/user-list";
    }

    //    ADD NEW USERS
    @PostMapping("/save")
    public @ResponseBody ResponseEntity<Boolean> saveUser(@RequestBody @Valid UserDto theUserDto) {
        User theUser = new User();
        theUser.setFullName(theUserDto.getFullName());
        theUser.setUserName(theUserDto.getUserName());
        theUser.setAddress(theUserDto.getAddress());
        theUser.setEmail(theUserDto.getEmail());
        theUser.setPhoneNumber(theUserDto.getPhoneNumber());
        theUser.setUserPassword(theUserDto.getUserPassword());
        int roleId = parseInt(theUserDto.getRole());
        Role theRole = new Role(roleId, roleId == 1 ? "Admin" : "User");
        roleService.save(theRole);
        theUser.setRole(theRole);
        userService.save(theUser);
        return ResponseEntity.ok(Boolean.TRUE);
    }



    //    SEARCH WITH PAGINATION
    // Khi click vao search
    // keyword2 để nhận biết có phải là nút search hay không
    // khi lùi phân trang mà bị mất keyword có nghĩa là phải phân biệt nút search và nút phân trang
    @GetMapping("/search")
    public String seachByKeywords(@RequestParam(required = false, name = "keyword") String keyword, @RequestParam(required = false, name = "keyword2") String keyword2, @PageableDefault(page = 0, size = 5) Pageable pageable, Model model) {

        if (keyword2 != null) {
            httpSession.setAttribute("keyword", keyword);
        } else {
            keyword = (String) httpSession.getAttribute("keyword");
        }

        Page<User> pageSearched = null;
        if (!(StringUtils.isEmpty(keyword))) {
            pageSearched = userService.findByKeywords(keyword, pageable);

        } else {
            pageSearched = userService.findAll(pageable);
        }
        List<User> listSearched = pageSearched.getContent();

        if (CollectionUtils.isEmpty(listSearched)) {
            String emptyMessage = msg.getMessage("users.list.empty", null, Locale.JAPAN);
            model.addAttribute("emptyMessage", emptyMessage);
        }
        model.addAttribute("pageSearched", PaginationHelper.createPagination(pageSearched));
        model.addAttribute("users", listSearched);
        return "admin/userEntity/user-search";
    }

    //    DELETE
    @GetMapping("/delete")
    public String delete(@RequestParam("userId") int theId) {
        userService.deleteById(theId);
        return "redirect:/users/list";
    }

    //    UPDATE
    @GetMapping("/update")
    public String update(@RequestParam("userId") int theId, Model theModel) {
        User user = userService.findByID(theId);
        theModel.addAttribute("user", user);
        return "admin/userEntity/user-update-form";
    }

    //    Save and Update
    @PostMapping("/saveAndUpdate")
    public @ResponseBody ResponseEntity<Boolean> saveAndUpdate(@RequestBody @Valid UserDtoUpdate theUserDtoUpdate) {
            User theUser = userService.findByID(theUserDtoUpdate.getId());
            theUser.setFullName(theUserDtoUpdate.getFullName());
            theUser.setAddress(theUserDtoUpdate.getAddress());
            theUser.setPhoneNumber(theUserDtoUpdate.getPhoneNumber());
            int roleId = parseInt(theUserDtoUpdate.getRole());
            Role theRole = new Role(roleId, roleId == 1 ? "Admin" : "User");
            roleService.save(theRole);
            theUser.setRole(theRole);
            userService.save(theUser);
        return ResponseEntity.ok(Boolean.TRUE);
    }

//    STATUS SWITCH
@PostMapping("/updateStatus")
public @ResponseBody ResponseEntity<Boolean> updateStatus(@RequestBody UserDto theUserDto) {
    User theUser = userService.findByID(theUserDto.getId());
    if (theUserDto.getUserStatus() == 1){
        theUser.setUserStatus(1);
    }else {
        theUser.setUserStatus(0);
    }
    userService.save(theUser);
    return ResponseEntity.ok(Boolean.TRUE);
}
}

