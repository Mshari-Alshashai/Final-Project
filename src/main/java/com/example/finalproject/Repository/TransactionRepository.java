package com.example.finalproject.Repository;

import com.example.finalproject.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTransactionByTransactionId(Integer transactionId);
}
