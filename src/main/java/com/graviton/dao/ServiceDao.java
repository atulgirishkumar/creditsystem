package com.graviton.dao;

import com.graviton.entity.CreditPackage;
import com.graviton.entity.Service;

import java.util.Optional;

/**
 * @author atul.girishkumar
 */
public interface ServiceDao {
    Optional<Service> findById(String serviceId);
    void save(Service service);
}
