package com.eadmin.building.service.repository;

import com.eadmin.building.service.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Repository’s job is to catch persistence specific exceptions and rethrow them as one of Spring’s unified unchecked exception.
public interface BuildingRepository extends JpaRepository<Building, Long> {

    Building findByBuildingId(Long id);
    List<Building> findAllByGroupId(Long id);
}
