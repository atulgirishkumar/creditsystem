package com.graviton.dao;

import com.graviton.entity.Customer;
import com.graviton.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * @author atul.girishkumar
 */
public interface CustomerDao {
    void modifyCredits(String customerId, int credits);
    Customer findById(String customerId) throws EntityNotFoundException;
}
