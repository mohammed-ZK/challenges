package com.challenges.challengeTwo.Report;

import java.math.BigDecimal;
import java.util.Map;

import com.challenges.challengeTwo.Entity.Product;

public class ProductReport {
private int inventoryStatus;
    private Map<Product, Integer> salesPerformance;
    private Map<Product, BigDecimal> pricingAnalysis;

    // Constructor
    public ProductReport(int inventoryStatus, Map<Product, Integer> salesPerformance, Map<Product, BigDecimal> pricingAnalysis) {
        this.inventoryStatus = inventoryStatus;
        this.salesPerformance = salesPerformance;
        this.pricingAnalysis = pricingAnalysis;
    }

    // Getters and setters
    public int getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(int inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Map<Product, Integer> getSalesPerformance() {
        return salesPerformance;
    }

    public void setSalesPerformance(Map<Product, Integer> salesPerformance) {
        this.salesPerformance = salesPerformance;
    }

    public Map<Product, BigDecimal> getPricingAnalysis() {
        return pricingAnalysis;
    }

    public void setPricingAnalysis(Map<Product, BigDecimal> pricingAnalysis) {
        this.pricingAnalysis = pricingAnalysis;
    }

}
