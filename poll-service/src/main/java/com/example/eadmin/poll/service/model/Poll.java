package com.example.eadmin.poll.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="polls", catalog = "polls")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pollId;

    private String description;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;

    private Long buildingId;

    private String status;
    private Date startDate;
    private Date endDate;
}
