package com.graviton.entity;

import com.graviton.db.InMemoryStore;

import java.sql.Timestamp;
import java.time.Instant;

/**
 * @author atul.girishkumar
 */
public class Transaction {

    Long id;
    String customerId;
    String transactionType;
    int credits;
    Instant timestamp;
    boolean isSuccess;
    String itemName;

    public Transaction(String customerId, String transactionType, int credits, String itemName) {
        this.id = InMemoryStore.getNextTransactionId();
        this.customerId = customerId;
        this.transactionType = transactionType;
        this.credits = credits;
        this.timestamp = Instant.now();
        this.itemName = itemName;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public int getCredits() {
        return credits;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public String getItemName() {
        return itemName;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @Override
    public String toString() {
        return (getIsSuccess() ? "":"[DENIED] ") + getCustomerId() + " " + getTransactionType() + " " + getCredits() + " credits";
    }
}

