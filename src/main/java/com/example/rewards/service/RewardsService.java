package com.example.rewards.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rewards.entity.Customer;
import com.example.rewards.entity.CustomerTransaction;
import com.example.rewards.model.CustomerRewards;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.CustomerTransactionRepository;

@Service

public class RewardsService {
	

	@Autowired
	private CustomerTransactionRepository customerTransactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public RewardsService(CustomerTransactionRepository customerTransactionRepository) {
		this.customerTransactionRepository = customerTransactionRepository;
	}

	public CustomerRewards calculateTotalRewards(Long customerId, LocalDate startDate, LocalDate endDate) {
		
		Customer custDetails = customerRepository.findByCustomerId(customerId);
		
		
		List<CustomerTransaction> transactions = customerTransactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);
		

		int total = 0;

		for (CustomerTransaction transaction : transactions) {

			total = total + calPoints(transaction.getAmount());
		}
		
		CustomerRewards rewards= new CustomerRewards();

		rewards.setCustomer(custDetails);
		rewards.setTotalLastThreeMonthRewards(total);
		
		return rewards;
	}

	
	public CustomerTransaction addTransaction(CustomerTransaction transaction){
		CustomerTransaction customerTransaction= customerTransactionRepository.save(transaction);
		return customerTransaction;
	}
	
	public CustomerTransaction editTransaction(CustomerTransaction customerTransaction) {
		CustomerTransaction editedTransaction= customerTransactionRepository.save(customerTransaction);
		return editedTransaction;
	}
	
	public void deleteTransaction(Long transactionId) {
		customerTransactionRepository.deleteById(transactionId);
	}


	private int calPoints(double amount) {

		int rewardPoints = 0;
		if (amount > 100) { 
			rewardPoints += (amount - 100) * 2;
			amount = 100;
		}
		if (amount > 50) {
			rewardPoints += (amount - 50);

		}
		return rewardPoints;
	}

	

}