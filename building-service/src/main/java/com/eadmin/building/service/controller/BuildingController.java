package com.eadmin.building.service.controller;

import com.eadmin.building.service.VO.ResponseTemplateVO;
import com.eadmin.building.service.model.Building;
import com.eadmin.building.service.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// class serves as a controller in Spring MVC
@RequestMapping("/building")
public class BuildingController {

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/get-all")
    public List<Building> getBuildings(){

        return buildingService.getBuildings();
    }

    @GetMapping("/{id}")
    public Building getBuildingById(@PathVariable Long id){
        return buildingService.getBuildingById(id);
    }

    @GetMapping("/by-groupId/{groupId}")
    public List<Building> getBuildingWithGroupId(@PathVariable Long groupId){
        return buildingService.getBuildingByGroupId(groupId);
    }


    @GetMapping("/buildings-and-presidents/{groupId}")
    public List<ResponseTemplateVO> getBuildingsAndPresidentsByGroupId(@PathVariable Long groupId){
        return buildingService.getBuildingsAndPresidentsByGroupId(groupId);
    }

    @GetMapping("/building-and-president/{buildingId}")
    public ResponseTemplateVO getBuildingAndPresident(@PathVariable Long buildingId){
        return buildingService.getBuildingsAndPresidentsByBuildingId(buildingId);
    }

    @GetMapping("/with-president-and-poll-and-tickets/{buildingId}")
    public ResponseTemplateVO getBuildingWithPresidentTicketsAndPolls(@PathVariable Long buildingId){
        return buildingService.getBuildingsWithPresidentAndTicketsAndPolls(buildingId);
    }


    @PostMapping("/add")
    public Building addBuilding(@RequestBody Building building){
        return buildingService.addBuilding(building);
    }

}
