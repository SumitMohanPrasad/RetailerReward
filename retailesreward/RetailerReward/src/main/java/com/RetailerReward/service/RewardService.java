package com.RetailerReward.service;

import java.util.Map;

public interface RewardService {
	Map<String, Object> calculateRewards(Long customerId);
}
