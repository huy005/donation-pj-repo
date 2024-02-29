package com.iiproject.donationpj.service;

import com.iiproject.donationpj.entity.Role;
import com.iiproject.donationpj.entity.User;
import com.iiproject.donationpj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);
    }

    @Override
    public Page<User> findByKeywords(String keyword, Pageable pageable) {
        return userRepository.findByKeywords(keyword, pageable);
    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }

    @Override
    public User findByID(int theId) {
        Optional<User> userResult = userRepository.findById(theId);
        User theUser = null;
        if (userResult.isPresent()) {
            theUser = userResult.get();
        } else {
            throw new RuntimeException("Did not find user id - " + theId);
        }
        return theUser;
    }

    @Override
    public void saveAndUpdate(User theUser) {
        userRepository.save(theUser);
    }

}
