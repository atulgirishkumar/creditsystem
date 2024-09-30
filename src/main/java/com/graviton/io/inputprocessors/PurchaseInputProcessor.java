package com.graviton.io.inputprocessors;

import com.graviton.controller.cmd.CreditController;
import com.graviton.controller.cmd.PricingController;

/**
 * @author atul.girishkumar
 */
public class PurchaseInputProcessor extends AbstractInputProcessor {

    CreditController creditController = new CreditController();

    @Override
    protected void processLine(String line) {
        String[] data = line.split(",");
        if (data.length == 2) {
            String customerId = data[0];
            String creditPackageId = data[1];

            getCreditController().buyCredits(customerId, creditPackageId);
            System.out.println("Customer " + customerId + " purchased " + creditPackageId + " package.");
        } else {
            System.err.println("Ignoring invalid line: " + line);
        }
    }

    protected CreditController getCreditController() {
        return creditController;
    }
}
