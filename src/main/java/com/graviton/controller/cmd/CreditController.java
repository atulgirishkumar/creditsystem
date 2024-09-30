package com.graviton.controller.cmd;

import com.graviton.exception.EntityNotFoundException;
import com.graviton.exception.InsufficientFundsException;
import com.graviton.service.CreditService;

/**
 * @author atul.girishkumar
 */
public class CreditController {

    private final CreditService creditsService = new CreditService();

    public void buyCredits(String customerId, String creditPackageId) {
        try {
            creditsService.buyCredits(customerId, creditPackageId);
        } catch (EntityNotFoundException e) {
            System.err.println("action=buyCredits, exception_message=" + e.getMessage());
        } catch (Exception e) {
            System.err.println("action=buyCredits, exception_message=" + e.getMessage());
        }
    }

    public void useCredits(String customerId, String serviceId, int usageFrequency) {
        try {
            creditsService.useCredits(customerId, serviceId, usageFrequency);
        } catch (InsufficientFundsException | EntityNotFoundException e) {
            System.err.println("action=useCredits, exception_message=" + e.getMessage());
        } catch (Exception e) {
            System.err.println("action=useCredits, exception_message=" + e.getMessage());
        }
    }
}
