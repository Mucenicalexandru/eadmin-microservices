package com.eadmin.building.service.controller;

import com.eadmin.building.service.VO.ResponseTemplateVO;
import com.eadmin.building.service.model.Building;
import com.eadmin.building.service.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping("/add")
    public Building addBuilding(@RequestBody Building building){
        return buildingService.addBuilding(building);
    }

}
