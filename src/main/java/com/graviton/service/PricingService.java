package com.graviton.service;

import com.graviton.dao.CreditPackageDao;
import com.graviton.dao.ServiceDao;
import com.graviton.dao.impl.CreditPackageDaoImpl;
import com.graviton.dao.impl.ServiceDaoImpl;
import com.graviton.entity.CreditPackage;
import com.graviton.entity.Service;

/**
 * @author atul.girishkumar
 */
public class PricingService {

    private final CreditPackageDao creditPackageDao = new CreditPackageDaoImpl();
    private final ServiceDao serviceDao = new ServiceDaoImpl();

    public void addServices(String serviceId, int creditsPerUsage) {
        Service service = new Service(serviceId, creditsPerUsage);
        serviceDao.save(service);
    }

    public void addCreditPackages(String packageName, int credits, double price) {
        CreditPackage creditPackage = new CreditPackage(packageName,credits,price);
        creditPackageDao.save(creditPackage);
    }
}
