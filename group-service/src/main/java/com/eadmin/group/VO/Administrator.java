package com.eadmin.group.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {

    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;

}
