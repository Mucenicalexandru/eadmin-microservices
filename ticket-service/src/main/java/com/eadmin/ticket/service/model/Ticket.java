package com.eadmin.ticket.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="tickets", catalog = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ticketId;

    private String title;
    private String details;
    private String actionTaken;
    private String department;

    private Long buildingId;
    private Long groupId;
    private Long assignedServiceProviderUserId;
    private Long userId;

    private Date dateOpened;
    private Date dateAccepted;
    private Date dateClosed;

    private int totalPrice;
    private String status;
    private String type;

    private String street;
    private String number;
    private String town;

}
