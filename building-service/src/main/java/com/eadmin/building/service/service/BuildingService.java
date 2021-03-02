package com.eadmin.building.service.service;

import com.eadmin.building.service.model.Building;
import com.eadmin.building.service.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BuildingService {

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

    public void deleteBuilding(Long id){
        buildingRepository.deleteByBuildingId(id);
    }


}
