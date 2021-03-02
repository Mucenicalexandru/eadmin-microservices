package com.eadmin.user.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="users", catalog = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String password;
    private String buildingStreet;
    private String buildingNumber;
    private String buildingName;
    private String buildingEntrance;
    private String town;
    private String country;
    private String other;
    private String department;
    private String company;
    private String website;
    private UserStatus userStatus;
    private Date joiningDate;

    private UserRole role;

    private Long groupId;
    private Long buildingId;

}
