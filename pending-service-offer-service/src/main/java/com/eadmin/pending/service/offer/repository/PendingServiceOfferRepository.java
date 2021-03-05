package com.eadmin.pending.service.offer.repository;

import com.eadmin.pending.service.offer.model.PendingServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PendingServiceOfferRepository extends JpaRepository<PendingServiceOffer, Long> {

    List<PendingServiceOffer> findAllByTicketId(Long ticketId);
    void deleteAllByTicketId(Long ticketId);
}
