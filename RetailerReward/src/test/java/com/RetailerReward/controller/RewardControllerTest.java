package com.RetailerReward.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import com.RetailerReward.service.RewardService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RewardService rewardService;

    private Map<String, Object> rewardResponse;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public RewardService rewardService() {
            return Mockito.mock(RewardService.class);
        }
    }

    @BeforeEach
    void setUp() {
        rewardResponse = new HashMap<>();
        rewardResponse.put("customerId", 1L);
        rewardResponse.put("totalRewards", 90);

        when(rewardService.calculateRewards(anyLong())).thenReturn(rewardResponse);
    }

    @Test
    void testGetRewards() throws Exception {
        mockMvc.perform(get("/reward/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.customerId").value(1))
               .andExpect(jsonPath("$.totalRewards").value(90));
    }
}