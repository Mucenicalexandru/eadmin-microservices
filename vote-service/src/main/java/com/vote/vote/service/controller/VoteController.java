package com.vote.vote.service.controller;

import com.vote.vote.service.model.Vote;
import com.vote.vote.service.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
