package com.graviton.dao.impl;

import com.graviton.dao.CustomerDao;
import com.graviton.dao.TransactionDAO;
import com.graviton.db.InMemoryStore;
import com.graviton.entity.Customer;
import com.graviton.entity.Transaction;

import java.util.*;

/**
 * @author atul.girishkumar
 */
public class TransactionDAOImpl implements TransactionDAO {
    Map<Long, Transaction> transactions = InMemoryStore.getTransactions();

    @Override
    public Transaction createPurchaseTransaction(String customerId, int credits, String itemName) {
        return createTransaction(customerId, credits, itemName, "PURCHASE");
    }

    @Override
    public Transaction createUsageTransaction(String customerId, int credits, String itemName) {
        return createTransaction(customerId, credits, itemName, "USE");
    }

    private Transaction createTransaction(String customerId, int credits, String itemName, String type){
        Transaction transaction = new Transaction(customerId, type, credits, itemName);
        transactions.put(transaction.getId(), transaction);
        return transaction;
    }

    @Override
    public void updateTransactionSuccess(Long transactionId) {
        transactions.get(transactionId).setSuccess(true);
    }

    @Override
    public void updateTransactionFailure(Long transactionId) {
        transactions.get(transactionId).setSuccess(false);
    }

    @Override
    public Map<Customer, List<Transaction>> listAll() {
        Map<Customer, List<Transaction>> customerTransactionMap = new HashMap<>();
        Map<String, Customer> customers = InMemoryStore.getCustomers();
        for (Transaction transaction : transactions.values()) {
            Customer customer = customers.get(transaction.getCustomerId());
            if(!customerTransactionMap.containsKey(customer)) {
                customerTransactionMap.put(customer, new ArrayList<>());
            }
            customerTransactionMap.get(customer).add(transaction);
        }

        for (List<Transaction> transactions : customerTransactionMap.values()) {
            transactions.sort(Comparator.comparing(Transaction::getId));
        }
        return customerTransactionMap;
    }


}
