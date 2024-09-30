package com.graviton.dao;

import com.graviton.entity.CreditPackage;

import java.util.Optional;

/**
 * @author atul.girishkumar
 */
public interface CreditPackageDao {
    Optional<CreditPackage> findById(String creditPackageId);
    void save(CreditPackage creditPackage);
}
