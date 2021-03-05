package com.eadmin.pending.service.offer.controller;

import com.eadmin.pending.service.offer.model.PendingServiceOffer;
import com.eadmin.pending.service.offer.service.PendingServiceOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pending-offer")
public class PendingServiceOfferController {

    @Autowired
    private PendingServiceOfferService pendingServiceOfferService;

    @GetMapping("/{ticketId}")
    public List<PendingServiceOffer> getAllByTicketId(@PathVariable Long ticketId){
        return pendingServiceOfferService.getAllByTicketId(ticketId);
    }

    @PostMapping("/add")
    public ResponseEntity<PendingServiceOffer> addOffer(@RequestBody PendingServiceOffer pendingServiceOffer){
        pendingServiceOfferService.addOffer(pendingServiceOffer);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/all/{ticketId}")
    public void deleteAllByTicketId(@PathVariable Long ticketId){
        pendingServiceOfferService.deleteOffers(ticketId);
    }

}
