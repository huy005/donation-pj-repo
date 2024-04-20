package com.iiproject.donationpj.service;

import com.iiproject.donationpj.entity.Role;
import com.iiproject.donationpj.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<User> findAll(Pageable pageable);
    void save(User theUser);
    Page<User> findByKeywords(String keyword, Pageable pageable);
    void deleteById(int theId);
    User findByID(int theId);
    void saveAndUpdate(User theUser);
}
