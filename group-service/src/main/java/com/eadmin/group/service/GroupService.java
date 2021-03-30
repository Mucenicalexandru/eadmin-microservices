package com.eadmin.group.service;

import com.eadmin.group.VO.Administrator;
import com.eadmin.group.VO.Censor;
import com.eadmin.group.VO.ResponseTemplateVO;
import com.eadmin.group.model.Group;
import com.eadmin.group.repository.GroupRepository;
import com.google.inject.internal.cglib.proxy.$CallbackFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<Group> addGroup(Group group){
        groupRepository.save(group);
        return ResponseEntity.ok().build();
    }

    public List<Group> getGroups(){
        return groupRepository.findAll();
    }

    public Group getGroupById(Long id){
        return groupRepository.getByGroupId(id);
    }

    public List<Group> getGroupsByTown(String town){
        return groupRepository.findAllByTown(town);
    }

    public ResponseTemplateVO getGroupAdministratorAndCensorByGroupId(Long id){
        ResponseTemplateVO response = new ResponseTemplateVO();
        Group group = groupRepository.getByGroupId(id);
        Administrator administrator = restTemplate.getForObject("http://USER-SERVICE/user/by-group-and-role/" + id + "/ADMINISTRATOR", Administrator.class);
        Censor censor = restTemplate.getForObject("http://USER-SERVICE/user/by-group-and-role/" + id + "/CENSOR", Censor.class);

        response.setGroup(group);
        response.setAdministrator(administrator);
        response.setCensor(censor);

        return response;
    }

    public void deleteGroup(Long groupId){
        String buildingUrl = "http://BUILDING-SERVICE/building/delete-all-by-group/" + groupId;
        String ticketUrl = "http://TICKET-SERVICE/ticket/delete-all-by-group/" + groupId;
        String userUrl = "http://USER-SERVICE/user/delete-by-group/" + groupId;
        restTemplate.delete(buildingUrl);
        restTemplate.delete(ticketUrl);
        restTemplate.delete(userUrl);
        groupRepository.deleteById(groupId);
    }
}
