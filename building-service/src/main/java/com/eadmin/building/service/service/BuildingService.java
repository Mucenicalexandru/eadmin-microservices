package com.eadmin.building.service.service;


import com.eadmin.building.service.VO.Poll;
import com.eadmin.building.service.VO.President;
import com.eadmin.building.service.VO.ResponseTemplateVO;
import com.eadmin.building.service.VO.Ticket;
import com.eadmin.building.service.model.Building;
import com.eadmin.building.service.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BuildingService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BuildingRepository buildingRepository;

    public Building addBuilding(Building building){
        return buildingRepository.save(building);
    }

    public List<Building> getBuildings(){
        return buildingRepository.findAll();
    }

    public Building getBuildingById(Long id){
        return buildingRepository.findByBuildingId(id);
    }

    public List<Building> getBuildingByGroupId(Long id){
        return buildingRepository.findAllByGroupId(id);
    }


    public List<ResponseTemplateVO> getBuildingsAndPresidentsByGroupId(Long groupId){

        List<ResponseTemplateVO> result = new ArrayList<>();

        List<Building> buildingsByGroup = buildingRepository.findAllByGroupId(groupId);

        for(Building building : buildingsByGroup){
            ResponseTemplateVO vo = new ResponseTemplateVO();
            vo.setBuilding(building);
            vo.setPresident(restTemplate.getForObject("http://USER-SERVICE/user/by-buildingId-and-role/" + building.getBuildingId() + "/PRESIDENT", President.class));
            result.add(vo);
        }

        return result;
    }

    public ResponseTemplateVO getBuildingsAndPresidentsByBuildingId(Long buildingId){

        ResponseTemplateVO result = new ResponseTemplateVO();

        Building building = buildingRepository.findByBuildingId(buildingId);

        result.setPresident(restTemplate.getForObject("http://USER-SERVICE/user/by-buildingId-and-role/" + buildingId + "/PRESIDENT", President.class));


        result.setBuilding(building);


        return result;
    }

    public ResponseTemplateVO getBuildingsWithPresidentAndTicketsAndPolls(Long buildingId){
        ResponseTemplateVO result = new ResponseTemplateVO();

        Building building = buildingRepository.findByBuildingId(buildingId);
        result.setBuilding(building);

        result.setPresident(restTemplate.getForObject("http://USER-SERVICE/user/by-buildingId-and-role/" + buildingId + "/PRESIDENT", President.class));


        Ticket[] tickets = (restTemplate.getForObject("http://TICKET-SERVICE/ticket/" + buildingId, Ticket[].class));
        assert tickets != null;
        result.setTicketList(Arrays.asList(tickets));

        Poll[] polls = (restTemplate.getForObject("http://POLL-SERVICE/poll/all-by-building/" + buildingId, Poll[].class));
        assert polls != null;
        result.setPollList(Arrays.asList(polls));

        return result;
    }


    public void deleteBuilding(Long id){
        buildingRepository.deleteByBuildingId(id);
    }


}
