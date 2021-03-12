package com.eadmin.user.service.VO;

import com.eadmin.user.service.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTemplateVO {

    private User user;
    private int totalReviews;
    private int averageStars;
}
