package com.eadmin.ticket.service.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingOffer {

    private Long pendingServiceOfferId;
    private Long ticketId;
    private Long serviceProviderUserId;
    private int serviceProviderPrice;
    private Date serviceProviderDate;
    private String serviceProviderFirstName;
    private String serviceProviderLastName;
    private String serviceProviderPhone;
    private String serviceProviderEmail;



}
