package com.graviton.service;

import com.graviton.dao.CreditPackageDao;
import com.graviton.dao.CustomerDao;
import com.graviton.dao.ServiceDao;
import com.graviton.dao.TransactionDAO;
import com.graviton.entity.CreditPackage;
import com.graviton.entity.Customer;
import com.graviton.entity.Service;
import com.graviton.entity.Transaction;
import com.graviton.exception.EntityNotFoundException;

import com.graviton.exception.InsufficientFundsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author atul.girishkumar
 */
class CreditServiceTest {

    private CreditService creditService;
    private TransactionDAO mockedTransactionDAO;
    private CustomerDao mockedCustomerDao;
    private CreditPackageDao mockedCreditPackageDao;
    private ServiceDao mockedServiceDao;

    @BeforeEach
    void setUp() {
        mockedTransactionDAO = mock(TransactionDAO.class);
        mockedCustomerDao = mock(CustomerDao.class);
        mockedCreditPackageDao = mock(CreditPackageDao.class);
        mockedServiceDao = mock(ServiceDao.class);

        creditService = new CreditService() {
            @Override
            public TransactionDAO getTransactionDAO() {
                return mockedTransactionDAO;
            }

            @Override
            public CustomerDao getCustomerDao() {
                return mockedCustomerDao;
            }

            @Override
            public CreditPackageDao getCreditPackageDao() {
                return mockedCreditPackageDao;
            }

            @Override
            public ServiceDao getServiceDao() {
                return mockedServiceDao;
            }
        };
    }

    @Test
    void testBuyCreditsSuccess() throws EntityNotFoundException {
        String customerId = "C1";
        String creditPackageId = "Basic";
        CreditPackage creditPackage = new CreditPackage(creditPackageId, 100, 100);
        Transaction transaction = new Transaction(customerId,"PURCHASE", 100, "S1");

        when(mockedCreditPackageDao.findById(creditPackageId)).thenReturn(Optional.of(creditPackage));
        when(mockedTransactionDAO.createPurchaseTransaction(eq(customerId), eq(100), eq(creditPackage.getPackageName())))
                .thenReturn(transaction);

        creditService.buyCredits(customerId, creditPackageId);

        verify(mockedCustomerDao).modifyCredits(customerId, 100);
        verify(mockedTransactionDAO).updateTransactionSuccess(transaction.getId());
    }

    @Test
    void testBuyCreditsInvalidPackage() {
        String customerId = "C1";
        String creditPackageId = "InvalidPackage";

        when(mockedCreditPackageDao.findById(creditPackageId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            creditService.buyCredits(customerId, creditPackageId);
        });

        assertEquals("Invalid Service Package", exception.getMessage());
    }

    @Test
    void testUseCreditsSuccess() throws InsufficientFundsException, EntityNotFoundException {
        String customerId = "C1";
        String serviceId = "S1";
        int frequency = 2;
        int creditsPerUsage = 50;
        Customer customer = new Customer(customerId);
        customer.addCredits(200);
        Service service = new Service(serviceId, creditsPerUsage);
        Transaction transaction = new Transaction(customerId,"USE", 100, "S1");

        when(mockedServiceDao.findById(serviceId)).thenReturn(Optional.of(service));
        when(mockedCustomerDao.findById(customerId)).thenReturn(customer);
        when(mockedTransactionDAO.createUsageTransaction(eq(customerId), eq(100), eq(serviceId)))
                .thenReturn(transaction);

        creditService.useCredits(customerId, serviceId, frequency);

        verify(mockedCustomerDao).modifyCredits(customerId, -100);
        verify(mockedTransactionDAO).updateTransactionSuccess(transaction.getId());
    }

    @Test
    void testUseCreditsInsufficientFunds() throws EntityNotFoundException {
        String customerId = "C1";
        String serviceId = "S1";
        int frequency = 3;
        Customer customer = new Customer(customerId);
        customer.addCredits(100);
        Service service = new Service(serviceId, 50);
        Transaction transaction = new Transaction(customerId,"USE", 150, "S1");

        when(mockedServiceDao.findById(serviceId)).thenReturn(Optional.of(service));
        when(mockedCustomerDao.findById(customerId)).thenReturn(customer);
        when(mockedTransactionDAO.createUsageTransaction(eq(customerId), eq(150), eq(serviceId)))
                .thenReturn(transaction);

        Exception exception = assertThrows(InsufficientFundsException.class, () -> {
            creditService.useCredits(customerId, serviceId, frequency);
        });

        assertEquals("Insufficient Funds", exception.getMessage());
        verify(mockedTransactionDAO).updateTransactionFailure(transaction.getId());
    }

    @Test
    void testUseCreditsInvalidService() {
        String customerId = "C1";
        String serviceId = "InvalidService";
        int frequency = 2;
        when(mockedServiceDao.findById(serviceId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            creditService.useCredits(customerId, serviceId, frequency);
        });

        assertEquals("Invalid Service", exception.getMessage());
    }

}