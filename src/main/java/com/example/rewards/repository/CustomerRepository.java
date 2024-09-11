package com.example.rewards.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.rewards.entity.Customer;



public interface CustomerRepository extends JpaRepository<Customer,Long>{

	Customer findByCustomerId(Long customerId);



}