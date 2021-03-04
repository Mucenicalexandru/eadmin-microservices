package com.eadmin.ticket.service.controller;

import com.eadmin.ticket.service.model.Ticket;
import com.eadmin.ticket.service.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/all")
    public List<Ticket> getAllTickets(){
        return ticketService.getAllTickets();
    }

    @GetMapping("/{buildingId}")
    public List<Ticket> getAllByBuildingId(@PathVariable Long buildingId){
        return ticketService.getTicketsByBuildingId(buildingId);
    }

    @GetMapping("/by-group/{groupId}")
    public List<Ticket> getAllByGroupId(@PathVariable Long groupId){
        return ticketService.getTicketsByGroupId(groupId);
    }

    @GetMapping("/get-all-filter/{status}/{type}/{buildingId}")
    public List<Ticket> getTicketsByDepartmentAndTown(@PathVariable String status, @PathVariable String type,@PathVariable Long buildingId){
        return ticketService.getTicketsByStatusAndTypeAndBuildingId(status, type, buildingId);
    }

    @PostMapping("/add")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
        return ticketService.addTicket(ticket);
    }
}
