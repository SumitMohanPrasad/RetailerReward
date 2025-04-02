package com.RetailerReward.serviceImpl;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.RetailerReward.Repository.TransactionRepository;
import com.RetailerReward.exception.CustomerNotFoundException;
import com.RetailerReward.model.Transaction;
import com.RetailerReward.service.RewardService;


@Service
public class RewardServiceImpl implements RewardService{

	private final TransactionRepository transactionRepository;
	
	public RewardServiceImpl(TransactionRepository transactionRepository) {
		this.transactionRepository=transactionRepository;
	}
	
	public Map<String, Object> calculateRewards(Long customerId){
		
		
		
		Map<String, Object>rewardSummary=new HashMap<>();
		Map<String, Integer>monthlyRewards=new HashMap<>();
		
		int totalPoints=0;
		
		boolean hasTransaction=false;
		
		for(int i=1;i<=3; i++) {
			LocalDate startDate=LocalDate.now().minusMonths(i).withDayOfMonth(1);
			LocalDate endDate=startDate.withDayOfMonth(startDate.lengthOfMonth());
			
		
			List<Transaction> transactions=transactionRepository.findByCustomerIdAndTransactionDateBetween(customerId,startDate,endDate);
			
			if(!transactions.isEmpty()) {
				hasTransaction=true;
			}
			
			int monthlyPoints=transactions.stream().mapToInt(this::calculateRewardPoints).sum();
			
			monthlyRewards.put(startDate.getMonth().name(), monthlyPoints);
			totalPoints+=monthlyPoints;
		}
		
		if(!hasTransaction) {
			throw new CustomerNotFoundException("No Transactions Found For Customer Id: " + customerId);
		}
		
		rewardSummary.put("CustomerId", customerId);
		rewardSummary.put("MonthlyRewards", monthlyRewards);
		rewardSummary.put("TotalPoints", totalPoints);
		return rewardSummary;
		
	}
	
	public int calculateRewardPoints(Transaction transaction) {
		double amount=transaction.getAmount();
		int points=0;
		
		if(amount>100) {
			points+=(int)((amount-100)*2);
			amount=100;
		}
		if(amount>50 ) {
			points+=(int)((amount-50)*1);
			
		}
		
		return points;
	}
}
