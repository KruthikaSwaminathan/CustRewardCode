package com.example.rewards.model;

import com.example.rewards.entity.Customer;

public class CustomerRewards {

	private int totalLastThreeMonthRewards;
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getTotalLastThreeMonthRewards() {
		return totalLastThreeMonthRewards;
	}

	public void setTotalLastThreeMonthRewards(int totalLastThreeMonthRewards) {
		this.totalLastThreeMonthRewards = totalLastThreeMonthRewards;
	}

}