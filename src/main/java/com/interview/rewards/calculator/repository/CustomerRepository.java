package com.interview.rewards.calculator.repository;

import com.interview.rewards.calculator.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**

 This interface represents the data access layer for the Customer entity using Spring Data JPA.
 It extends the JpaRepository interface, which provides out-of-the-box basic CRUD operations.
 This repository is responsible for persisting and retrieving Customer objects from the database.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}