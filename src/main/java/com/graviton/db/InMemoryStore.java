package com.graviton.db;

import com.graviton.entity.CreditPackage;
import com.graviton.entity.Customer;
import com.graviton.entity.Service;
import com.graviton.entity.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author atul.girishkumar
 */
public class InMemoryStore {

    private static Map<String, Service> services = new HashMap<>();
    private static Map<String, CreditPackage> packages = new HashMap<>();
    private static Map<String, Customer> customers = new HashMap<>();
    private static Map<Long, Transaction> transactions = new HashMap<>();
    private static final AtomicLong transactionId = new AtomicLong(0);

    public static Map<String, CreditPackage> getPackages() {
        return packages;
    }

    public static Map<String, Customer> getCustomers() {
        return customers;
    }

    public static Map<String, Service> getServices() {
        return services;
    }

    public static Map<Long, Transaction> getTransactions() {
        return transactions;
    }

    public static long getNextTransactionId() {
        return transactionId.incrementAndGet();
    }
}
