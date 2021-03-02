package com.eadmin.building.service.controller;

import com.eadmin.building.service.model.Building;
import com.eadmin.building.service.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/by-groupId/{groupId}")
    public List<Building> getBuildingWithGroupId(@PathVariable Long groupId){
        return buildingService.getBuildingByGroupId(groupId);
    }

    @PostMapping("/add")
    public Building addBuilding(@RequestBody Building building){
        return buildingService.addBuilding(building);
    }
}
