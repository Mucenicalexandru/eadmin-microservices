package com.eadmin.ticket.service.service;

import com.eadmin.ticket.service.VO.PendingOffer;
import com.eadmin.ticket.service.VO.ResponseTemplateVO;
import com.eadmin.ticket.service.VO.User;
import com.eadmin.ticket.service.model.Ticket;
import com.eadmin.ticket.service.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private RestTemplate restTemplate;

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



    public ResponseEntity<Ticket> addTicket(Ticket ticket, String status){
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        ticket.setDateOpened(currentDate);
        ticket.setStatus(status);

        ticketRepository.save(ticket);
        return ResponseEntity.ok().build();
    }

    public List<ResponseTemplateVO> getTicketsByDepartmentAndTownWithPendingOffersInfo(String department, String town){
        List<ResponseTemplateVO> result = new ArrayList<>();

        List<Ticket> ticketList = ticketRepository.findAllByDepartmentAndTownAndStatus(department, town, "opened");

        for(Ticket ticket : ticketList){
            ResponseTemplateVO vo = new ResponseTemplateVO();
            vo.setTicket(ticket);
            vo.setPendingOffer(restTemplate.getForObject("http://PENDINGOFFER-SERVICE/pending-offer/" + ticket.getTicketId(), PendingOffer[].class));
            result.add(vo);
        }

        return result;
    }

    public List<ResponseTemplateVO> getAllByGroupWithPendingOffersInfo(Long groupId, String status, String type){
        List<ResponseTemplateVO> result = new ArrayList<>();

        List<Ticket> ticketList = ticketRepository.findAllByGroupIdAndStatusAndType(groupId, status, type);

        for(Ticket ticket : ticketList){
            ResponseTemplateVO vo = new ResponseTemplateVO();
            vo.setTicket(ticket);
            vo.setPendingOffer(restTemplate.getForObject("http://PENDINGOFFER-SERVICE/pending-offer/" + ticket.getTicketId(), PendingOffer[].class));
            result.add(vo);
        }

        return result;
    }

    public List<ResponseTemplateVO> getTicketsByGroupAndStatusWithPendingOffers(Long groupId, String status, String type){
        List<ResponseTemplateVO> result = new ArrayList<>();

        List<Ticket> ticketList = ticketRepository.findAllByGroupIdAndStatusAndType(groupId, status, type);

        for(Ticket ticket : ticketList){
            ResponseTemplateVO vo = new ResponseTemplateVO();
            vo.setTicket(ticket);
            vo.setPendingOffer(restTemplate.getForObject("http://PENDINGOFFER-SERVICE/pending-offer/" + ticket.getTicketId(), PendingOffer[].class));
            result.add(vo);
        }

        return result;
    }


    public ResponseTemplateVO getTicketByIdWithPendingOfferInfo(Long ticketId){
        ResponseTemplateVO result = new ResponseTemplateVO();

        Ticket ticket = ticketRepository.findByTicketId(ticketId);
        PendingOffer[] pendingOfferList = restTemplate.getForObject("http://PENDINGOFFER-SERVICE/pending-offer/" + ticket.getTicketId(), PendingOffer[].class);

        for(PendingOffer pending : pendingOfferList){
            User user = restTemplate.getForObject("http://USER-SERVICE/user/" + pending.getServiceProviderUserId(), User.class);

            pending.setServiceProviderFirstName(user.getFirstName());
            pending.setServiceProviderLastName(user.getLastName());
            pending.setServiceProviderPhone(user.getPhone());
            pending.setServiceProviderEmail(user.getEmail());
        }

        result.setTicket(ticket);
        result.setPendingOffer(pendingOfferList);

        return result;
    }

    public void deleteTicketByUserId(Long userId){
        List<Ticket> ticketsToDelete = ticketRepository.findAllByUserId(userId);

        for(Ticket ticket : ticketsToDelete){
            ticketRepository.deleteById(ticket.getTicketId());
            restTemplate.delete("http://PENDINGOFFER-SERVICE/pending-offer/all/" + ticket.getTicketId());
        }
    }

    public void deleteTicketsByBuildingId(Long buildingId){
        List<Ticket> ticketsToDelete = ticketRepository.findAllByBuildingId(buildingId);

        for(Ticket ticket : ticketsToDelete){
            ticketRepository.deleteById(ticket.getTicketId());
            restTemplate.delete("http://PENDINGOFFER-SERVICE/pending-offer/all/" + ticket.getTicketId());
        }
    }

    public void deleteTicketsByGroupId(Long groupId){
        List<Ticket> ticketsToDelete = ticketRepository.findAllByGroupId(groupId);

        for(Ticket ticket : ticketsToDelete){
            ticketRepository.deleteById(ticket.getTicketId());
            restTemplate.delete("http://PENDINGOFFER-SERVICE/pending-offer/all/" + ticket.getTicketId());
        }
    }
}
