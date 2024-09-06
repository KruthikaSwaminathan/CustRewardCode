package com.example.rewards.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rewards.entity.CustomerTransaction;
import com.example.rewards.repository.CustomerTransactionRepository;

@Service

public class RewardsService {

	@Autowired
	private CustomerTransactionRepository customerTransactionRepository;

	public RewardsService(CustomerTransactionRepository customerTransactionRepository) {
		this.customerTransactionRepository = customerTransactionRepository;
	}

	public Map<String, Integer> calculateTotalRewards(Long customerId, LocalDate startDate, LocalDate endDate) {

		List<CustomerTransaction> transactions = customerTransactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, startDate, endDate);

		int total = 0;

		for (CustomerTransaction transaction : transactions) {

			total = total + calPoints(transaction.getAmount());
		}

		Map<String, Integer> points = new HashMap<>();
		points.put("total", total);
		return points;
	}

	private int calPoints(double amount) {

		int rewardPoints = 0;
		if (amount > 100) { // 500
			rewardPoints += (amount - 100) * 2;
			amount = 100;
		}
		if (amount > 50) {
			rewardPoints += (amount - 50);

		}
		return rewardPoints;
	}

}