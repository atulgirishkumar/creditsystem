package com.graviton.dao;

import com.graviton.entity.Customer;
import com.graviton.entity.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @author atul.girishkumar
 */
public interface TransactionDAO {

    Transaction createPurchaseTransaction(String customerId, int credits, String itemName);
    Transaction createUsageTransaction(String customerId, int credits, String itemName);
    void updateTransactionSuccess(Long transactionId);
    void updateTransactionFailure(Long transactionId);
    Map<Customer, List<Transaction>> listAll();
}
