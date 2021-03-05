package com.eadmin.ticket.service.VO;

import com.eadmin.ticket.service.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTemplateVO {

    private PendingOffer[] pendingOffer;
    private Ticket ticket;
}
