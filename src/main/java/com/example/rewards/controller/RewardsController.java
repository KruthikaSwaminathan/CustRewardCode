package com.example.rewards.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewards.entity.Customer;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.service.RewardsService;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Autowired
   private  RewardsService rewardsService;
   
   public RewardsController(RewardsService rewardsService){
	   this.rewardsService=rewardsService;
   }


    @GetMapping(value = "/customer/{customerId}/total")
    public  Map<String,Integer> getRewards(@PathVariable Long customerId){
  
       LocalDate threemonthsdata=LocalDate.now().minusMonths(3);
	   LocalDate now=LocalDate.now();
        
        return  rewardsService.calculateTotalRewards(customerId, threemonthsdata, now);
    }

}