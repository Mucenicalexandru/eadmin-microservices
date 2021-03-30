package com.eadmin.user.service.repository;

import com.eadmin.user.service.model.User;
import com.eadmin.user.service.model.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserId(Long id);
    boolean existsByEmail(String email);
    User findByEmail(String email);
    List<User> findAllByRolesAndTown(String role, String town);
    List<User> findAllByRoles(String role);
    User findByGroupIdAndRoles(Long groupId, String Role);
    User findByBuildingIdAndRoles(Long id, String role);
    List<User> findAllByGroupIdAndUserStatus(Long groupId, UserStatus userStatus);
    List<User> findAllByRolesAndTownAndDepartment(String role, String town, String department);
    List<User> findAllByGroupId(Long groupId);
    List<User> findAllByBuildingId(Long buildingId);

}
