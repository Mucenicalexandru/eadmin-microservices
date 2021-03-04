package com.eadmin.building.service.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String buildingStreet;
    private String buildingNumber;
    private String town;
    private String country;
}
