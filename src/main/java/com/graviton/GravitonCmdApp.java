package com.graviton;

import com.graviton.dao.CustomerDao;
import com.graviton.dao.TransactionDAO;
import com.graviton.dao.impl.CustomerDaoImpl;
import com.graviton.dao.impl.TransactionDAOImpl;
import com.graviton.entity.Customer;
import com.graviton.entity.Transaction;
import com.graviton.io.inputprocessors.InputProcessor;
import com.graviton.io.inputprocessors.PricingInputProcessor;
import com.graviton.io.inputprocessors.PurchaseInputProcessor;
import com.graviton.io.inputprocessors.UsageInputProcessor;
import com.graviton.io.outputprocessors.CustomerTransactionHistoryOutputProcessor;
import com.graviton.io.outputprocessors.OutputProcessor;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author atul.girishkumar
 */
public class GravitonCmdApp {

    public static void main(String[] args) throws IOException {
        InputProcessor pricingInputProcessor = new PricingInputProcessor();
        pricingInputProcessor.processInput(args[0]);

        InputProcessor purchaseInputProcessor = new PurchaseInputProcessor();
        purchaseInputProcessor.processInput(args[1]);

        InputProcessor usageInputProcessor = new UsageInputProcessor();
        usageInputProcessor.processInput(args[2]);

        TransactionDAO transactionDAO = new TransactionDAOImpl();
        Map<Customer, List<Transaction>> transactionHistory = transactionDAO.listAll();

        OutputProcessor<Map<Customer, List<Transaction>>> outputProcessor = new CustomerTransactionHistoryOutputProcessor();
        outputProcessor.processOutput(args[3],transactionHistory);
    }
}
