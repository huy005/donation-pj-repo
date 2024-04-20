package com.iiproject.donationpj.service;

import com.iiproject.donationpj.entity.Role;
import com.iiproject.donationpj.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RoleService {
    void save(Role theRole);
}
