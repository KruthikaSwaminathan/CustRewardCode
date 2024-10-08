package com.example.rewards.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.rewards.entity.Customer;
import com.example.rewards.entity.CustomerTransaction;

@Repository
@Transactional
public interface CustomerTransactionRepository extends JpaRepository<CustomerTransaction, Long> {

	List<CustomerTransaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, LocalDate startDate,
			LocalDate endDate);

	CustomerTransaction findByCustomerId(Long customerId);

	CustomerTransaction findByTransactionId(Long transactionId);

}