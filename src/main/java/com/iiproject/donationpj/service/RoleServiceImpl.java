package com.iiproject.donationpj.service;

import com.iiproject.donationpj.entity.Role;
import com.iiproject.donationpj.entity.User;
import com.iiproject.donationpj.repository.RoleRepository;
import com.iiproject.donationpj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public void save(Role theRole) {
        roleRepository.save(theRole);
    }
}
