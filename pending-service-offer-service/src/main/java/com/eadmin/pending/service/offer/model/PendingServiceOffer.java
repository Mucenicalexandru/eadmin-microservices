package com.eadmin.pending.service.offer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="pendingoffers", catalog = "pendingoffers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingServiceOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pendingServiceOfferId;

    private Long ticketId;
    private Long serviceProviderUserId;
    private Date serviceProviderDate;
    private int serviceProviderPrice;
}
