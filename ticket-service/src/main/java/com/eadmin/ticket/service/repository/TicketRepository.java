package com.eadmin.ticket.service.repository;

import com.eadmin.ticket.service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Ticket findByTicketId(Long ticketId);
    List<Ticket> findAllByUserId(Long userId);
    List<Ticket> findAllByBuildingId(Long buildingId);
    List<Ticket> findAllByGroupId(Long groupId);
    List<Ticket> findAllByGroupIdAndStatusAndType(Long groupId, String status, String type);
    List<Ticket> findAllByUserIdAndStatusAndType(Long userId, String status, String type);
    List<Ticket> findAllByDepartmentAndTownAndStatus (String department, String town, String status);
    List<Ticket> findAllByStatusAndTypeAndBuildingId(String status, String type, Long buildingId);
    List<Ticket> findAllByAssignedServiceProviderUserIdAndStatus(Long serviceProviderId, String status);
}
