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

    public void addVote(Vote vote){
        voteRepository.save(vote);
    }

    public void deleteAllByPollId(Long pollId){
        List<Vote> votesToDelete = voteRepository.findAllByPollId(pollId);

        for(Vote vote : votesToDelete){
            voteRepository.deleteById(vote.getVoteId());
        }
    }
}
