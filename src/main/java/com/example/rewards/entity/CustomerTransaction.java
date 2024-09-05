package com.example.rewards.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST_TRANS")
public class CustomerTransaction{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 @Column(name = "TRANSACTION_ID")
    private Long transactionId;
@Column(name = "CUSTOMER_ID")
private Long customerId;
@Column(name = "SPENT_DETAILS")
private String spentDetails;
@Column(name = "AMOUNT")
private double amount;
@Column(name = "TRANSACTION_DATE")
private LocalDate transactionDate;
public LocalDate getTransactionDate() {
	return transactionDate;
}
public void setTransactionDate(LocalDate transactionDate) {
	this.transactionDate = transactionDate;
}
public Long getTransactionId() {
	return transactionId;
}
public void setTransactionId(Long transactionId) {
	this.transactionId = transactionId;
}
public Long getCustomerId() {
	return customerId;
}
public void setCustomerId(Long customerId) {
	this.customerId = customerId;
}
public String getSpentDetails() {
	return spentDetails;
}
public void setSpentDetails(String spentDetails) {
	this.spentDetails = spentDetails;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}


}