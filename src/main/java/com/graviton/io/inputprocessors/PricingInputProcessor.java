package com.graviton.io.inputprocessors;

import com.graviton.controller.cmd.PricingController;

/**
 * @author atul.girishkumar
 */
public class PricingInputProcessor extends AbstractInputProcessor {

    private final PricingController pricingController = new PricingController();
    private static final String SERVICE_KEYWORD = "service";
    private static final String PACKAGE_KEYWORD = "package";

    @Override
    protected void processLine(String line) {
        String[] data = line.split(",");
        if (data.length > 0) {
            switch (data[0].toLowerCase()) {
                case SERVICE_KEYWORD:
                    handleService(data);
                    break;
                case PACKAGE_KEYWORD:
                    handlePackage(data);
                    break;
                default:
                    System.err.println("Ignoring unknown pricing info: " + line);
            }
        }else {
            System.err.println("Ignoring invalid line: " + line);
        }
    }

    private void handleService(String[] data) {
        if(data.length == 3){
            String serviceId = data[1];
            int creditsPerUsage = Integer.parseInt(data[2]);
            getPricingController().addServices(serviceId, creditsPerUsage);
            System.out.println("Service " + serviceId + " costs " + creditsPerUsage + " credits per usage.");
        }else{
            System.err.println("Ignoring invalid line: " + data);
        }
    }

    private void handlePackage(String[] data) {
        if(data.length == 4){
            String packageName = data[1];
            int credits = Integer.parseInt(data[2]);
            double price = Double.parseDouble(data[3]);
            getPricingController().addCreditPackages(packageName, credits, price);
            System.out.println(packageName + " Package: " + credits + " credits for $" + price);
        }else {
            System.err.println("Ignoring invalid line: " + data);
        }
    }

    // Will be chagned with dependency injection
    protected PricingController getPricingController() {
        return pricingController;
    }
}
