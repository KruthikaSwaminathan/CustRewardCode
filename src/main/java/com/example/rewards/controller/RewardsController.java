package com.example.rewards.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rewards.entity.Customer;
import com.example.rewards.entity.CustomerTransaction;
import com.example.rewards.model.CustomerRewards;
import com.example.rewards.repository.CustomerRepository;
import com.example.rewards.repository.CustomerTransactionRepository;
import com.example.rewards.service.RewardsService;

@RestController
@RequestMapping("/retailer")
public class RewardsController {

	@Autowired
	private RewardsService rewardsService;

	@Autowired
	private CustomerTransactionRepository customerTransactionRepository;

	public RewardsController(RewardsService rewardsService) {
		this.rewardsService = rewardsService;
	}

	@GetMapping(value = "/customer/{customerId}/monthlyRewards")
	public ResponseEntity<CustomerRewards> getMonthlyRewards(@PathVariable Long customerId) {

		LocalDate threemonthsdata = LocalDate.now().minusMonths(3);
		LocalDate now = LocalDate.now();

		CustomerRewards rewards = rewardsService.calculateTotalRewards(customerId, threemonthsdata, now);
		return new ResponseEntity<>(rewards, HttpStatus.OK);
	}

	@PostMapping(value = "/customer/addTransaction/{customerId}")
	public ResponseEntity<CustomerTransaction> addTransactions(@PathVariable Long customerId,
			@RequestBody CustomerTransaction customerTransaction) {

		return ResponseEntity.ok(rewardsService.addTransaction(customerTransaction));
	}

	@PutMapping(value = "/customer/editTransaction/{transactionId}")
	public ResponseEntity<CustomerTransaction> editTransactions(@PathVariable Long transactionId,
			@RequestBody CustomerTransaction customerTransaction) {

		CustomerTransaction tran = (CustomerTransaction) customerTransactionRepository
				.findByTransactionId(transactionId);

		tran.setAmount(customerTransaction.getAmount());
		tran.setSpentDetails(customerTransaction.getSpentDetails());

		return ResponseEntity.ok(rewardsService.editTransaction(tran));
	}

	@DeleteMapping(value = "/customer/deleteTransaction/{transactionId}")
	public ResponseEntity<String> deleteTransaction(@PathVariable Long transactionId) {

		rewardsService.deleteTransaction(transactionId);

		return ResponseEntity.ok("Transaction deleted successfully");
	}

}