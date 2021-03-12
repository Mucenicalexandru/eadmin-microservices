package com.edmin.review.service.controller;

import com.edmin.review.service.model.Review;
import com.edmin.review.service.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/all")
    public List<Review> getAll(){
        return reviewService.getAll();
    }

    @GetMapping("/by-provider/{id}")
    public List<Review> getAllByProviderId(@PathVariable Long id){
        return reviewService.getAllByProviderId(id);
    }

    @PostMapping("/add")
    public void addReview(@RequestBody Review review){
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        review.setDate(currentDate);
        reviewService.addReview(review);
    }
}
