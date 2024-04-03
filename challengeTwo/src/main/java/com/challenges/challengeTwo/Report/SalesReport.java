package com.challenges.challengeTwo.Report;

import java.math.BigDecimal;
import java.util.List;

import com.challenges.challengeTwo.Entity.Product;
import com.challenges.challengeTwo.Entity.Seller;

public class SalesReport {
    private int totalNumberOfSales;
    private BigDecimal totalRevenue;
    private List<Product> topSellingProducts;
    private List<Seller> topPerformingSellers;

    // Constructor
    public SalesReport(int totalNumberOfSales, BigDecimal totalRevenue, List<Product> topSellingProducts, List<Seller> topPerformingSellers) {
        this.totalNumberOfSales = totalNumberOfSales;
        this.totalRevenue = totalRevenue;
        this.topSellingProducts = topSellingProducts;
        this.topPerformingSellers = topPerformingSellers;
    }

    // Getters and setters
    public int getTotalNumberOfSales() {
        return totalNumberOfSales;
    }

    public void setTotalNumberOfSales(int totalNumberOfSales) {
        this.totalNumberOfSales = totalNumberOfSales;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public List<Product> getTopSellingProducts() {
        return topSellingProducts;
    }

    public void setTopSellingProducts(List<Product> topSellingProducts) {
        this.topSellingProducts = topSellingProducts;
    }

    public List<Seller> getTopPerformingSellers() {
        return topPerformingSellers;
    }

    public void setTopPerformingSellers(List<Seller> topPerformingSellers) {
        this.topPerformingSellers = topPerformingSellers;
    }
}
