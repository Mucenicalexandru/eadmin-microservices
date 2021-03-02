package com.eadmin.group.repository;

import com.eadmin.group.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Group getByGroupId(Long id);
    void deleteByGroupId(Long id);
}
