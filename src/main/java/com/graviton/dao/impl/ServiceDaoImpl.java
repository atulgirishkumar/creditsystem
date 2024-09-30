package com.graviton.dao.impl;

import com.graviton.dao.ServiceDao;
import com.graviton.db.InMemoryStore;
import com.graviton.entity.Customer;
import com.graviton.entity.Service;

import java.util.Map;
import java.util.Optional;

/**
 * @author atul.girishkumar
 */
public class ServiceDaoImpl implements ServiceDao {
    Map<String, Service> services = InMemoryStore.getServices();

    @Override
    public Optional<Service> findById(String serviceId) {
        return Optional.ofNullable(services.get(serviceId));
    }

    @Override
    public void save(Service service) {
        services.put(service.getServiceId(), service);
    }
}
