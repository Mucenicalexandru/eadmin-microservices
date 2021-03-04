package com.eadmin.ticket.service.repository;

import com.eadmin.ticket.service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findByTicketId(Long ticketId);
    List<Ticket> findAllByBuildingId(Long buildingId);
    List<Ticket> findAllByGroupId(Long groupId);
    List<Ticket> findAllByDepartmentAndTownAndStatus (String department, String town, String status);
    List<Ticket> findAllByStatusAndTypeAndBuildingId(String status, String type, Long buildingId);
}
