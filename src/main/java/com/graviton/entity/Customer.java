package com.graviton.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 * @author atul.girishkumar
 */
public class Customer {
    private String customerId;
    private int availableCredits;

    public Customer(String customerId) {
        this.customerId = customerId;
        this.availableCredits = 0;
    }

    public void addCredits(int credits) {
        this.availableCredits += credits;
    }

    public int getAvailableCredits() {
        return availableCredits;
    }

    public String getCustomerId() {
        return customerId;
    }
}

