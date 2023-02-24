package com.interview.rewards.calculator.repository;

import com.interview.rewards.calculator.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**

 Repository interface for performing CRUD operations on Transaction entity.
 This interface extends JpaRepository which provides default implementations for basic CRUD operations.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
