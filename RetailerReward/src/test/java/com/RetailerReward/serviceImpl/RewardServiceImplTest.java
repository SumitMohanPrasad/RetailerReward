package com.RetailerReward.serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.RetailerReward.Repository.TransactionRepository;
import com.RetailerReward.exception.CustomerNotFoundException;
import com.RetailerReward.model.Customer;
import com.RetailerReward.model.Transaction;

@ExtendWith(MockitoExtension.class)
public class RewardServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardServiceImpl rewardService;

    private Transaction transaction1;
    private Transaction transaction2;
    private Customer customer;

    @BeforeEach
    void setUp() {
    	customer = new Customer();
        customer.setId(1L);
        customer.setName("Customer1");
	    transaction1 = new Transaction(1L, customer, 120.0, LocalDate.now().minusMonths(1));
	    transaction2 = new Transaction(2L, customer, 70.0, LocalDate.now().minusMonths(2));
       
    }

    @Test
    void testCalculateRewards() {
        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(List.of(transaction1,transaction2));

        Map<String, Object> rewards = rewardService.calculateRewards(1L);
        
        assertNotNull(rewards);
        assertEquals(1L, rewards.get("CustomerId"));
        assertTrue(((Map<?, ?>) rewards.get("MonthlyRewards")).size() > 0);
    }

    @Test
    void testCalculateRewardsNoTransactions() {
        when(transactionRepository.findByCustomerIdAndTransactionDateBetween(anyLong(), any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.emptyList());

        Exception exception = assertThrows(CustomerNotFoundException.class, () -> rewardService.calculateRewards(1L));
        assertEquals("No Transactions Found For Customer Id: 1", exception.getMessage());
    }

    @Test
    void testCalculateRewardPoints() {
        int points1 = rewardService.calculateRewardPoints(transaction1);
        int points2 = rewardService.calculateRewardPoints(transaction2);
        
        assertEquals(90, points1); 
        assertEquals(20, points2); 
    }
}
