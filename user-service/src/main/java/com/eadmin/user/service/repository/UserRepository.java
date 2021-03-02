package com.eadmin.user.service.repository;

import com.eadmin.user.service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserId(Long id);
    boolean existsByEmail(String email);
}
