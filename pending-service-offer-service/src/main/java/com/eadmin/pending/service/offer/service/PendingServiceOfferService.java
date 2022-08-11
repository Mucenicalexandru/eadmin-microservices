package com.eadmin.pending.service.offer.service;

import com.eadmin.pending.service.offer.model.PendingServiceOffer;
import com.eadmin.pending.service.offer.repository.PendingServiceOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PendingServiceOfferService {

    @Autowired
    private PendingServiceOfferRepository pendingServiceOfferRepository;


    public List<PendingServiceOffer> getAllByTicketId(Long ticketId){
        return pendingServiceOfferRepository.findAllByTicketId(ticketId);
    }

    public void addOffer(PendingServiceOffer pendingServiceOffer){
        pendingServiceOfferRepository.save(pendingServiceOffer);
    }

    public void deleteOffers(Long ticketId){
        List <PendingServiceOffer> offerList = pendingServiceOfferRepository.findAllByTicketId(ticketId);
        for(PendingServiceOffer offer : offerList){
            pendingServiceOfferRepository.deleteById(offer.getPendingServiceOfferId());
        }
    }

    public void deleteSingleOffer(Long pendingServiceOfferId){
        pendingServiceOfferRepository.deleteById(pendingServiceOfferId);
    }


}
