package com.example.eadmin.poll.service.VO;

import com.example.eadmin.poll.service.model.Poll;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseTemplateVO {

    private Poll poll;
    private int totalVotes;
    private List<Long> usersWhoAlreadyVoted;

}
