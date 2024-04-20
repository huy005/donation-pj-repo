package com.iiproject.donationpj.repository;


import com.iiproject.donationpj.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);

    @Query("select u from User u where u.email like %:keyword% " +
            "or u.phoneNumber like %:keyword% " +
            "or u.userName like %:keyword% " +
            "or u.address like %:keyword% " +
            "or u.role like %:keyword% " +
            "or u.userStatus like %:keyword% "  +
            "order by u.id asc")
    Page<User> findByKeywords(@Param("keyword") String keyword, Pageable pageable);
}
