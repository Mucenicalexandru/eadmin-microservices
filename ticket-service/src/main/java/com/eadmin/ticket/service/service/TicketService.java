package com.eadmin.ticket.service.service;

import com.eadmin.ticket.service.model.Ticket;
import com.eadmin.ticket.service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }


    public Ticket getTicketById(Long ticketId){
        return ticketRepository.findByTicketId(ticketId);
    }

    public List<Ticket> getTicketsByBuildingId(Long buildingId){
        return ticketRepository.findAllByBuildingId(buildingId);
    }

    public List<Ticket> getTicketsByGroupId(Long groupId){
        return ticketRepository.findAllByGroupId(groupId);
    }

    public List<Ticket> getTicketsByStatusAndTypeAndBuildingId(String status, String type, Long buildingId){
        return ticketRepository.findAllByStatusAndTypeAndBuildingId(status, type, buildingId);
    }

    public List<Ticket> getTicketsByDepartmentAndTown(String department, String town, String status){
        return ticketRepository.findAllByDepartmentAndTownAndStatus(department, town, status);
    }

    public ResponseEntity<Ticket> addTicket(Ticket ticket){
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ticket.setDateOpened(currentDate);
        ticket.setStatus("opened");

        ticketRepository.save(ticket);
        return ResponseEntity.ok().build();
    }
}
