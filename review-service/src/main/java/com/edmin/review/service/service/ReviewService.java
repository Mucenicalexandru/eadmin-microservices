package com.edmin.review.service.service;

import com.edmin.review.service.model.Review;
import com.edmin.review.service.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(Review review){
        reviewRepository.save(review);
    }

    public List<Review> getAll(){
        return reviewRepository.findAll();
    }

    public List<Review> getAllByProviderId(Long providerId){
        return reviewRepository.findAllByProviderId(providerId);
    }


}
