package com.graviton.dao.impl;

import com.graviton.dao.CustomerDao;
import com.graviton.db.InMemoryStore;
import com.graviton.entity.Customer;
import com.graviton.exception.EntityNotFoundException;

import java.util.*;

/**
 * @author atul.girishkumar
 */
public class CustomerDaoImpl implements CustomerDao{
    Map<String, Customer> customers = InMemoryStore.getCustomers();

    @Override
    public void modifyCredits(String customerId, int credits){
        if(!customers.containsKey(customerId)){
            createCustomer(customerId);
        }
        customers.get(customerId).addCredits(credits);
    }

    @Override
    public Customer findById(String customerId) throws EntityNotFoundException {
        if(!customers.containsKey(customerId)){
            createCustomer(customerId);
        }
        return customers.get(customerId);
    }

    private void createCustomer(String customerId){
        Customer customer = new Customer(customerId);
        customers.put(customerId, customer);
    }
}
