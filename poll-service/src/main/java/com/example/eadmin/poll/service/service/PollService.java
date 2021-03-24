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
        List<Long> listOfUsersWhoAlreadyVote;


        //we iterate through each poll and we call VOTE-SERVICE by poll id to find out how many votes a poll has
        //we iterate through each vote so we make a list with all the users who already voted
        //then we build a new object with the results
        for(Poll poll : pollList){
            ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
            listOfUsersWhoAlreadyVote = new ArrayList<>();
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

    public HashMap<String, Integer> getResults(Long pollId){

        HashMap<String, Integer> results = new HashMap<>();
        results.put("option1", 0);
        results.put("option2", 0);
        results.put("option3", 0);
        results.put("option4", 0);
        results.put("option5", 0);


        Vote[] listOfVotes = restTemplate.getForObject("http://VOTE-SERVICE/vote/get-all/" + pollId, Vote[].class);
        assert listOfVotes != null;
        for(Vote vote : listOfVotes){
            if(vote.getAnswerOption().equals("option1")){
                results.put("option1", results.get("option1") + 1);
            }else if(vote.getAnswerOption().equals("option2")){
                results.put("option2", results.get("option3") + 1);
            }else if(vote.getAnswerOption().equals("option3")){
                results.put("option3", results.get("option4") + 1);
            }else if(vote.getAnswerOption().equals("option4")){
                results.put("option4", results.get("option4") + 1);
            }else if(vote.getAnswerOption().equals("option5")){
                results.put("option5", results.get("option5") + 1);
            }
        }

        return results;
    }

    public List<Poll> getTicketsByBuildingAndStatus(Long buildingId, String status){
        return pollRepository.findAllByBuildingIdAndStatus(buildingId, status);
    }

    public void addPoll(Poll poll, String status){
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        poll.setStartDate(currentDate);

        poll.setStatus(status);
        pollRepository.save(poll);
    }
}
