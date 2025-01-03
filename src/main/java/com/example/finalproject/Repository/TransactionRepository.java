package com.example.finalproject.Repository;

import com.example.finalproject.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findTransactionByTransactionId(Integer transactionId);

    List<Transaction> findTransactionsByGame_Id(Integer gameId);

}
