package com.graviton.io.inputprocessors;

import com.graviton.controller.cmd.CreditController;

/**
 * @author atul.girishkumar
 */
public class UsageInputProcessor extends AbstractInputProcessor {

    CreditController creditController = new CreditController();

    @Override
    protected void processLine(String line) {
        String[] data = line.split(",");
        if (data.length == 3) {
            String customerId = data[0];
            String serviceId = data[1];
            int usageFrequency = Integer.parseInt(data[2]);

            getCreditController().useCredits(customerId, serviceId, usageFrequency);
            System.out.println("Customer " + customerId + " used service " + serviceId + " " + usageFrequency + " times.");
        } else {
            System.err.println("Ignoring invalid line: " + line);
        }
    }

    protected CreditController getCreditController() {
        return creditController;
    }
}
