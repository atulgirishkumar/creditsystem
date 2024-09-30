package com.graviton.service;

import com.graviton.dao.CreditPackageDao;
import com.graviton.dao.CustomerDao;
import com.graviton.dao.ServiceDao;
import com.graviton.dao.TransactionDAO;
import com.graviton.dao.impl.CreditPackageDaoImpl;
import com.graviton.dao.impl.CustomerDaoImpl;
import com.graviton.dao.impl.ServiceDaoImpl;
import com.graviton.dao.impl.TransactionDAOImpl;
import com.graviton.entity.CreditPackage;
import com.graviton.entity.Customer;
import com.graviton.entity.Service;
import com.graviton.entity.Transaction;
import com.graviton.exception.EntityNotFoundException;
import com.graviton.exception.InsufficientFundsException;
import java.util.Optional;

/**
 * @author atul.girishkumar
 */
public class CreditService {

    // TODO: Create via Dependency Injection
    private final TransactionDAO transactionDAO = new TransactionDAOImpl();
    private final CustomerDao customerDao = new CustomerDaoImpl();
    private final CreditPackageDao creditPackageDao = new CreditPackageDaoImpl();
    private final ServiceDao serviceDao = new ServiceDaoImpl();

    public void buyCredits(String customerId, String creditPackageId) throws EntityNotFoundException {
        Optional<CreditPackage> creditPackage = getCreditPackageDao().findById(creditPackageId);
        if(creditPackage.isPresent()){
            int credits = creditPackage.get().getCredits();
            // TODO: take transactionType from enum
            Transaction transaction = getTransactionDAO().createPurchaseTransaction(customerId, credits, creditPackage.get().getPackageName());
            getCustomerDao().modifyCredits(customerId, credits);
            getTransactionDAO().updateTransactionSuccess(transaction.getId());
        }else{
            throw new EntityNotFoundException("Invalid Service Package");
        }
    }

    public void useCredits(String customerId, String serviceId, int frequency) throws InsufficientFundsException, EntityNotFoundException {
        Optional<Service> service = getServiceDao().findById(serviceId);
        if(service.isPresent()){
            Customer customer = getCustomerDao().findById(customerId);

            int creditsPerUsage = service.get().getCreditsPerUsage();
            int requiredCredits = creditsPerUsage * frequency;
            int customerCredits = customer.getAvailableCredits();

            Transaction transaction = getTransactionDAO().createUsageTransaction(customerId, requiredCredits, service.get().getServiceId());
            if(customerCredits >= requiredCredits){
                getCustomerDao().modifyCredits(customerId, -requiredCredits);
                getTransactionDAO().updateTransactionSuccess(transaction.getId());
            }else{
                getTransactionDAO().updateTransactionFailure(transaction.getId());
                throw new InsufficientFundsException("Insufficient Funds");
            }
        }else{
            throw new EntityNotFoundException("Invalid Service");
        }
    }

    // Will be removed this with Dependency Injection, Added for test cases
    public CreditPackageDao getCreditPackageDao() {
        return creditPackageDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    public ServiceDao getServiceDao() {
        return serviceDao;
    }

    public TransactionDAO getTransactionDAO() {
        return transactionDAO;
    }
}
