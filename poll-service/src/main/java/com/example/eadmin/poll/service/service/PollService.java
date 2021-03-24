package com.example.eadmin.poll.service.service;

import com.example.eadmin.poll.service.VO.ResponseTemplateVO;
import com.example.eadmin.poll.service.VO.User;
import com.example.eadmin.poll.service.VO.Vote;
import com.example.eadmin.poll.service.model.Poll;
import com.example.eadmin.poll.service.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Poll getPollById(Long id){
        return pollRepository.findByPollId(id);
    }

    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

    public List<Poll> getAllPollsByBuildingId(Long buildingId){
        return pollRepository.findAllByBuildingId(buildingId);
    }

    public List<ResponseTemplateVO> getAllPollsByBuildingIdWithNumberOfVotes(Long buildingId){
        List<ResponseTemplateVO> result = new ArrayList<>();

        List<Poll> pollList =  pollRepository.findAllByBuildingId(buildingId);
        List<Long> listOfUsersWhoAlreadyVote = new ArrayList<>();


        //we iterate through each poll and we call VOTE-SERVICE by poll id to find out how many votes a poll has
        //we iterate through each vote so we make a list with all the users who already voted
        //then we build a new object with the results
        for(Poll poll : pollList){
            ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
            List<Vote> voteList = Arrays.asList(Objects.requireNonNull(restTemplate.getForObject("http://VOTE-SERVICE/vote/get-all/" + poll.getPollId(), Vote[].class)));
            for(Vote vote : voteList){
                listOfUsersWhoAlreadyVote.add(vote.getUserId());
            }
            responseTemplateVO.setPoll(poll);
            responseTemplateVO.setTotalVotes(voteList.size());
            responseTemplateVO.setUsersWhoAlreadyVoted(listOfUsersWhoAlreadyVote);
            result.add(responseTemplateVO);
        }


        return result;

    }

    public List<Poll> getTicketsByBuildingAndStatus(Long buildingId, String status){
        return pollRepository.findAllByBuildingIdAndStatus(buildingId, status);
    }

    public ResponseEntity<Poll> addPoll(Poll poll, String status){
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        poll.setStartDate(currentDate);

        poll.setStatus(status);
        pollRepository.save(poll);
        return ResponseEntity.ok().build();
    }
}
