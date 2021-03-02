package com.eadmin.group.service;

import com.eadmin.group.model.Group;
import com.eadmin.group.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    public Group addGroup(Group group){
        return groupRepository.save(group);
    }

    public List<Group> getGroups(){
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id){
        return groupRepository.getByGroupId(id);
    }

    public void deleteGroup(Long id){
        groupRepository.deleteByGroupId(id);
    }
}
