package com.graviton.entity;

/**
 * @author atul.girishkumar
 */
public class CreditPackage {
    private String packageName;
    private int credits;
    private double price;

    public CreditPackage(String packageName, int credits, double price) {
        this.packageName = packageName;
        this.credits = credits;
        this.price = price;
    }

    public int getCredits() {
        return credits;
    }

    public double getPrice() {
        return price;
    }

    public String getPackageName() {
        return packageName;
    }
}