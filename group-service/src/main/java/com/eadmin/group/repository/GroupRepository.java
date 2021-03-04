package com.eadmin.group.repository;

import com.eadmin.group.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group getByGroupId(Long id);
    void deleteByGroupId(Long id);
    List<Group> findAllByTown(String town);
}
