package com.example.eadmin.poll.service.controller;

import com.example.eadmin.poll.service.VO.ResponseTemplateVO;
import com.example.eadmin.poll.service.model.Poll;
import com.example.eadmin.poll.service.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/poll")
public class PollController {

    @Autowired
    private PollService pollService;

    @GetMapping("/all")
    public List<Poll> getAll(){
        return pollService.getAllPolls();
    }

    @GetMapping("/{id}")
    public Poll getPollById(@PathVariable Long id){
        return pollService.getPollById(id);
    }

    @GetMapping("/all-by-building/{id}")
    public List<Poll> getAllByBuildingId(@PathVariable Long id){
        return pollService.getAllPollsByBuildingId(id);
    }

    @GetMapping("/all-by-building-with-total-votes/{buildingId}")
    public List<ResponseTemplateVO> getAllByBuildingWithVoteNumber(@PathVariable Long buildingId){
        return pollService.getAllPollsByBuildingIdWithNumberOfVotes(buildingId);
    }

    @GetMapping("/get-all-filter/{buildingId}/{status}")
    public List<Poll> getTicketsByBuildingAndStatus(@PathVariable Long buildingId, @PathVariable  String status){
        return pollService.getTicketsByBuildingAndStatus(buildingId, status);
    }

    @GetMapping("/results/{pollId}")
    public HashMap<String, Integer> getResults(@PathVariable Long pollId){
        return pollService.getResults(pollId);
    }


    @PostMapping("/add")
    public ResponseEntity<Poll> addPoll(@RequestBody Poll poll){
        pollService.addPoll(poll, "active");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/close/{pollId}")
    public ResponseEntity<Poll> closeTicket(@PathVariable Long pollId){
        Poll pollToUpdate = pollService.getPollById(pollId);

        pollService.addPoll(pollToUpdate, "inactive");
        return ResponseEntity.ok().build();
    }
}
