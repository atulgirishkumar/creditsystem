package com.graviton.entity;

/**
 * @author atul.girishkumar
 */
public class Service {
    private final String serviceId;
    private final int creditsPerUsage;

    public Service(String serviceId, int creditsPerUsage) {
        this.serviceId = serviceId;
        this.creditsPerUsage = creditsPerUsage;
    }

    public int getCreditsPerUsage() {
        return creditsPerUsage;
    }

    public String getServiceId() {
        return serviceId;
    }
}

