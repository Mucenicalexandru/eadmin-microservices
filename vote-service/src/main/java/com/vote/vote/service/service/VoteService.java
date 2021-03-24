package com.vote.vote.service.service;

import com.vote.vote.service.model.Vote;
import com.vote.vote.service.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    public List<Vote> getAllVotesByPollId(Long pollId){
        return voteRepository.findAllByPollId(pollId);
    }
}
