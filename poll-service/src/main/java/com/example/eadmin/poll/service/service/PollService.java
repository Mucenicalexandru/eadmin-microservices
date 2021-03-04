package com.example.eadmin.poll.service.service;

import com.example.eadmin.poll.service.model.Poll;
import com.example.eadmin.poll.service.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    public Poll getPollById(Long id){
        return pollRepository.findByPollId(id);
    }

    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }

    public List<Poll> getAllPollsByBuildingId(Long buildingId){
        return pollRepository.findAllByBuildingId(buildingId);
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
