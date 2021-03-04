package com.example.eadmin.poll.service.repository;

import com.example.eadmin.poll.service.model.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    Poll findByPollId(Long pollId);
    List<Poll> findAllByBuildingId(Long buildingId);
    List<Poll> findAllByBuildingIdAndStatus(Long buildingId, String status);
}
