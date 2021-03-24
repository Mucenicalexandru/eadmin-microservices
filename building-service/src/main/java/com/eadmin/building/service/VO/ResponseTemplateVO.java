package com.eadmin.building.service.VO;

import com.eadmin.building.service.model.Building;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTemplateVO {

    private President president;
    private Building building;
    private List<Poll> pollList = new ArrayList<>();
    private List<Ticket> ticketList = new ArrayList<>();
}
