package com.graviton.io.outputprocessors;

import com.graviton.entity.Customer;
import com.graviton.entity.Transaction;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author atul.girishkumar
 */
public class CustomerTransactionHistoryOutputProcessor extends AbstractOutputProcessor<Map<Customer, List<Transaction>>>{

    @Override
    protected String formatData(Map<Customer, List<Transaction>> data) {
        StringBuilder formattedData = new StringBuilder();
        data.forEach((customer, transactions) -> {
            formattedData.append("Customer: ").append(customer.getCustomerId()).append("\n")
                    .append("Transactions:\n");

            transactions.forEach(transaction ->
                    formattedData.append(transaction).append("\n")
            );

            formattedData.append("Remaining Credits: ").append(customer.getAvailableCredits()).append("\n\n");
        });
        return formattedData.toString();
    }
}
