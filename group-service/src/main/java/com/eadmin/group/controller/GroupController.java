package com.eadmin.group.controller;

import com.eadmin.group.model.Group;
import com.eadmin.group.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/get-all")
    public List<Group> getGroups(){
        return groupService.getGroups();
    }

    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable Long id){
        return groupService.getGroupById(id);
    }

    @PostMapping("/add")
    public Group addGroup(@RequestBody Group group){
        return groupService.addGroup(group);
    }

    @DeleteMapping("/{id}")
    public void deleteGroup(@PathVariable Long id){

        groupService.deleteGroup(id);
    }

}
