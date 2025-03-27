package com.RetailerReward.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.RetailerReward.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	List<Transaction> findByCustomerIdAndTransactionDateBetween(Long customerId,
			LocalDate startDate, LocalDate endDate);

	List<Transaction> findByCustomerId(Long customerId);

}
