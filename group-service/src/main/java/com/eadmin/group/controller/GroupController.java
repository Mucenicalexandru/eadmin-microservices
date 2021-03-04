package com.eadmin.group.controller;

import com.eadmin.group.VO.ResponseTemplateVO;
import com.eadmin.group.model.Group;
import com.eadmin.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/get-all")
    public List<Group> getGroups(){
        return groupService.getGroups();
    }

    @GetMapping("/get-by-id/{id}")
    public Group getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }

    @GetMapping("/get-by-town/{town}")
    public List<Group> getGroupsByTown(@PathVariable String town){
        return groupService.getGroupsByTown(town);
    }

    @GetMapping("/group-administrator-censor/{groupId}")
    public ResponseTemplateVO getGroupAdministratorAndCensorByGroupId(@PathVariable Long groupId){
        return groupService.getGroupAdministratorAndCensorByGroupId(groupId);
    }

    @PostMapping("/add")
    public ResponseEntity<Group> addGroup(@RequestBody Group group){
        return groupService.addGroup(group);
    }

    @PutMapping("/edit-group/{id}")
    public ResponseEntity<Group> editGroup(@PathVariable Long id, @RequestBody Group group) {
        Group groupToUpdate = groupService.getGroupById(id);

        groupToUpdate.setOfficialName(group.getOfficialName());
        groupToUpdate.setShortName(group.getShortName());
        groupToUpdate.setEmail(group.getEmail());
        groupToUpdate.setPicture(group.getPicture());
        groupToUpdate.setIban(group.getIban());

        return groupService.addGroup(groupToUpdate);
    }


    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id){

        groupService.deleteGroup(id);
    }

}
