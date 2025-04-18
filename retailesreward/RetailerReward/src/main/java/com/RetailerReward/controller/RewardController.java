package com.RetailerReward.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RetailerReward.service.RewardService;

@RestController
@RequestMapping("/reward")
public class RewardController {

	 private final RewardService rewardService;

	    public RewardController(RewardService rewardService) {
	        this.rewardService = rewardService;
	    }

	    @GetMapping("/{customerId}")
	    public Map<String, Object> getRewards(@PathVariable("customerId") Long customerId) {
	        return rewardService.calculateRewards(customerId);
	    }
}
