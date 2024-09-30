package com.graviton.controller.cmd;

import com.graviton.service.PricingService;

/**
 * @author atul.girishkumar
 */
public class PricingController {

    private final PricingService pricingService = new PricingService();

    public void addServices(String serviceId, int creditsPerUsage) {
        try {
            pricingService.addServices(serviceId, creditsPerUsage);
        } catch (Exception e) {
            System.err.println("action=addServices, exception_message=" + e.getMessage());
        }
    }

    public void addCreditPackages(String packageName, int credits, double price) {
        try {
            pricingService.addCreditPackages(packageName, credits, price);
        } catch (Exception e) {
            System.err.println("action=addCreditPackages, exception_message=" + e.getMessage());
        }
    }
}
