package com.example.rewards.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CUST")
public class Customer{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "CUSTOMER_ID")
private Long customerId;
@Column(name = "CUSTOMER_NAME")
private String customerName;
@Column(name = "CUSTOMER_ADDR")
private String customerAddr;

}