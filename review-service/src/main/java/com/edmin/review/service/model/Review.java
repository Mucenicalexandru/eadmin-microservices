package com.edmin.review.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="reviews", catalog = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reviewId;

    private Long providerId;

    private String title;
    private int starNumber;
    private String review;
    private Date date;

    //firstName and lastName will remain even if an user will be deleted from the application
    private String givingUserFirstName;
    private String givingUserLastName;


}
