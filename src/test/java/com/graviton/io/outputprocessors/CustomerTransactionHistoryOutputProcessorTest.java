package com.graviton.io.outputprocessors;

import com.graviton.entity.Customer;
import com.graviton.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author atul.girishkumar
 */
class CustomerTransactionHistoryOutputProcessorTest {

    private CustomerTransactionHistoryOutputProcessor outputProcessor;

    @BeforeEach
    void setUp() {
        outputProcessor = new CustomerTransactionHistoryOutputProcessor();
    }

    @Test
    void testFormatData() {
        Customer C1 = new Customer("C1");
        C1.addCredits(90);

        Map<Customer, List<Transaction>> data = new HashMap<>();
        data.put(C1, getOneCustomerTransactions(C1));

        String formattedOutput = outputProcessor.formatData(data);
        System.out.println(formattedOutput);

        String expectedOutput =
                "Customer: C1\n" +
                        "Transactions:\n" +
                        "C1 PURCHASE 100 credits\n" +
                        "C1 USE 10 credits\n" +
                        "[DENIED] C1 USE 200 credits\n" +
                        "Remaining Credits: 90";

        assertEquals(expectedOutput.trim(), formattedOutput.trim());
    }

    private List<Transaction> getOneCustomerTransactions(Customer customer) {
        List<Transaction> transactionHistory = new ArrayList<>();
        Transaction transaction1 = new Transaction(customer.getCustomerId(), "PURCHASE", 100, "Basic");
        transaction1.setSuccess(true);

        Transaction transaction2 = new Transaction(customer.getCustomerId(), "USE", 10, "S1");
        transaction2.setSuccess(true);

        Transaction transaction3 = new Transaction(customer.getCustomerId(), "USE", 200, "S2");
        transaction3.setSuccess(false);

        transactionHistory.add(transaction1);
        transactionHistory.add(transaction2);
        transactionHistory.add(transaction3);
        return transactionHistory;
    }

}