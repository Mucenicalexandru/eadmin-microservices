package com.vote.vote.service.controller;

import com.vote.vote.service.model.Vote;
import com.vote.vote.service.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/get-all/{pollId}")
    public List<Vote> getAllVotesByPollId(@PathVariable Long pollId){
        return voteService.getAllVotesByPollId(pollId);
    }

    @PostMapping("/")
    public ResponseEntity<Vote> addVote(@RequestBody Vote vote){
        voteService.addVote(vote);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-by-poll/{pollId}")
    public void deleteAllByPollId(@PathVariable Long pollId){
        voteService.deleteAllByPollId(pollId);
    }
}
