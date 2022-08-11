package com.eadmin.ticket.service.controller;

import com.eadmin.ticket.service.VO.PendingOffer;
import com.eadmin.ticket.service.VO.ResponseTemplateVO;
import com.eadmin.ticket.service.model.Ticket;
import com.eadmin.ticket.service.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
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

    @GetMapping("/by-id/{ticketId}")
    public Ticket getTicketById(@PathVariable Long ticketId){
        return ticketService.getTicketById(ticketId);
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
    public List<Ticket> getTicketsByStatusAndTypeAndBuilding(@PathVariable String status, @PathVariable String type, @PathVariable Long buildingId){
        return ticketService.getTicketsByStatusAndTypeAndBuildingId(status, type, buildingId);
    }

    @GetMapping("/{department}/{town}")
    public List<Ticket> getTicketsByDepartmentAndTown(@PathVariable String department, @PathVariable String town){
        return ticketService.getTicketsByDepartmentAndTown(department, town, "opened");
    }

    @GetMapping("/{department}/{town}/with-offers")
    public List<ResponseTemplateVO> getTicketsByDepartmentAndTownWithPendingOffers(@PathVariable String department, @PathVariable String town){
        return ticketService.getTicketsByDepartmentAndTownWithPendingOffersInfo(department, town);
    }

    @GetMapping("/{ticketId}/with-offers")
    public ResponseTemplateVO getTicketByIdWithPendingOfferInfo(@PathVariable Long ticketId){
        return ticketService.getTicketByIdWithPendingOfferInfo(ticketId);
    }

    @GetMapping("/all-by-group-with-pending-offers/{groupId}/{status}/{type}")
    public List<ResponseTemplateVO> getAllByGroupWithPendingOffersInfo(@PathVariable Long groupId, @PathVariable String status, @PathVariable String type){
        return ticketService.getAllByGroupWithPendingOffersInfo(groupId, status, type);
    }

    @GetMapping("/all-by-group-and-status-with-pending-offers/{groupId}/{status}")
    public List<ResponseTemplateVO> getTicketsByGroupAndStatus(@PathVariable Long groupId, @PathVariable String status){
        return ticketService.getTicketsByGroupAndStatusWithPendingOffers(groupId, status, "Administrative");
    }

    @GetMapping("/all-by-user-with-pending-offers/{userId}/{status}")
    public List<ResponseTemplateVO> getTicketsByUserIdAndStatusWithPendingOffers(@PathVariable Long userId, @PathVariable String status){
        return ticketService.getTicketsByUserIdAndStatusWithPendingOffers(userId, status, "Personal");
    }

    @GetMapping("/assigned-service-provider/{serviceProviderId}/{status}")
    public List<Ticket> getWonTickets(@PathVariable Long serviceProviderId, @PathVariable String status){
        return ticketService.getWonTicketsByServiceProvider(serviceProviderId, status);
    }

    @PostMapping("/add")
    public ResponseEntity<Ticket> addTicket(@RequestBody Ticket ticket){
        return ticketService.addTicket(ticket, "opened");
    }

    @PutMapping("/accept-offer/{ticketId}")
    public ResponseEntity<Ticket> acceptOffer(@PathVariable Long ticketId, @RequestBody PendingOffer pendingOffer){
        Ticket ticketToUpdate = ticketService.getTicketById(ticketId);
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        ticketToUpdate.setDateAccepted(currentDate);
        ticketToUpdate.setAssignedServiceProviderUserId(pendingOffer.getServiceProviderUserId());
        ticketToUpdate.setTotalPrice(pendingOffer.getServiceProviderPrice());

        return ticketService.addTicket(ticketToUpdate, "in progress");
    }

    @PutMapping("/close/{ticketId}")
    public ResponseEntity<Ticket> closeTicket(@PathVariable Long ticketId){
        Ticket ticketToUpdate = ticketService.getTicketById(ticketId);
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        ticketToUpdate.setDateClosed(currentDate);

        return ticketService.addTicket(ticketToUpdate, "closed");
    }

    @DeleteMapping("/delete-all-by-userId/{userId}")
    public void deleteAllByUserId(@PathVariable Long userId){
        ticketService.deleteTicketByUserId(userId);
    }

    @DeleteMapping("/delete-all-by-building/{buildingId}")
    public void deleteAllByBuildingId(@PathVariable Long buildingId){
        ticketService.deleteTicketsByBuildingId(buildingId);
    }


    @DeleteMapping("/delete-all-by-group/{groupId}")
    public void deleteAllByGroupId(@PathVariable Long groupId){
        ticketService.deleteTicketsByGroupId(groupId);
    }
}
