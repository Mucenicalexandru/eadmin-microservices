package com.eadmin.building.service.repository;

import com.eadmin.building.service.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    Building findByBuildingId(Long id);
    void deleteByBuildingId(Long id);
    List<Building> findAllByGroupId(Long id);
}
