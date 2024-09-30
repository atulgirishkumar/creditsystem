package com.graviton.dao.impl;

import com.graviton.dao.CreditPackageDao;
import com.graviton.db.InMemoryStore;
import com.graviton.entity.CreditPackage;
import com.graviton.entity.Customer;

import java.util.Map;
import java.util.Optional;

/**
 * @author atul.girishkumar
 */
public class CreditPackageDaoImpl implements CreditPackageDao {
    Map<String, CreditPackage> creditPackages = InMemoryStore.getPackages();

    @Override
    public Optional<CreditPackage> findById(String creditPackageId) {
        return Optional.ofNullable(creditPackages.get(creditPackageId));
    }

    @Override
    public void save(CreditPackage creditPackage) {
        creditPackages.put(creditPackage.getPackageName(),creditPackage);
    }
}
