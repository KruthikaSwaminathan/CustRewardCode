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
import com.example.rewards.exception.ResourseNotFoundException;
import com.example.rewards.model.CustomerRewards;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.CustomerTransactionRepository;

@Service

public class RewardsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardsService.class);

	@Autowired
	private CustomerTransactionRepository customerTransactionRepository;

	@Autowired
	private CustomerRepository customerRepository;

	public RewardsService(CustomerTransactionRepository customerTransactionRepository) {
		this.customerTransactionRepository = customerTransactionRepository;
	}

	public CustomerRewards calculateTotalRewards(Long customerId, LocalDate startDate, LocalDate endDate) {

		LOGGER.info("Calculating Total rewards for last 3 months of a customer:start");

		Customer custDetails = customerRepository.findByCustomerId(customerId);

		if (custDetails == null) {
			throw new ResourseNotFoundException("Customer does not exist");
		}

		List<CustomerTransaction> transactions = customerTransactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);

		if (transactions == null) {
			throw new ResourseNotFoundException("Transaction does not exist");
		}

		int total = 0;

		for (CustomerTransaction transaction : transactions) {

			total = total + calPoints(transaction.getAmount());
		}

		CustomerRewards rewards = new CustomerRewards();

		rewards.setCustomer(custDetails);
		rewards.setTotalLastThreeMonthRewards(total);
		LOGGER.info("Calculating Total rewards for last 3 months of a customer:end");

		return rewards;

	}

	public CustomerTransaction addTransaction(CustomerTransaction transaction) {
		LOGGER.info("Adding transaction:start");
		CustomerTransaction customerTransaction = customerTransactionRepository.save(transaction);
		LOGGER.info("Adding transaction:end");
		return customerTransaction;
	}

	public CustomerTransaction editTransaction(CustomerTransaction customerTransaction) {
		LOGGER.info("editing transaction:start");
		CustomerTransaction editedTransaction = customerTransactionRepository.save(customerTransaction);
		LOGGER.info("editing transaction:end");
		return editedTransaction;
	}

	public void deleteTransaction(Long transactionId) {
		LOGGER.info("deleting transaction:start");
		customerTransactionRepository.deleteById(transactionId);
		LOGGER.info("deleting transaction:end");
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