package com.challenges.challengeTwo.Report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenges.challengeTwo.Entity.Client;
import com.challenges.challengeTwo.Entity.Product;
import com.challenges.challengeTwo.Entity.Sale;
import com.challenges.challengeTwo.Entity.Seller;
import com.challenges.challengeTwo.Entity.Transaction;
import com.challenges.challengeTwo.Repository.Impl.SaleService;
import com.challenges.challengeTwo.Repository.Interfaces.ClientRepository;
import com.challenges.challengeTwo.Repository.Interfaces.ProductRepository;
import com.challenges.challengeTwo.Repository.Interfaces.SaleRepository;

@Service
public class ReportService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleService saleService;

    public SalesReport generateSalesReport(Date startDate, Date endDate) {
        // Fetch sales data from the database
        List<Sale> sales = saleRepository.findByCreationDateBetween(startDate, endDate);

        // Calculate total number of sales
        int totalSales = sales.size();

        // Calculate total revenue
        BigDecimal totalRevenue = sales.stream().map(Sale::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add);

        // Calculate top selling products
        List<Product> topSellingProducts = calculateTopSellingProducts(sales);

        // Calculate top performing sellers
        List<Seller> topPerformingSellers = calculateTopPerformingSellers(sales);

        // Create and return the sales report
        return new SalesReport(totalSales, totalRevenue, topSellingProducts, topPerformingSellers);
    }

    public ClientReport generateClientReport() {
        // Fetch client-related data from the database
        List<Client> clients = clientRepository.findAll();

        // Calculate total number of clients
        int totalClients = clients.size();

        // Calculate top spending clients
        List<Client> topSpendingClients = calculateTopSpendingClients(clients);

        // Calculate client activity
        Map<Client, Integer> clientActivity = calculateClientActivity(clients);

        // Calculate client location statistics
        Map<String, Integer> clientLocationStatistics = calculateClientLocationStatistics(clients);

        // Create and return the client report
        return new ClientReport(totalClients, topSpendingClients, clientActivity, clientLocationStatistics);
    }

    public ProductReport generateProductReport() {
        // Fetch product-related data from the database
        List<Product> products = productRepository.findAll();

        // Calculate inventory status
        int inventoryStatus = calculateInventoryStatus(products);

        // Calculate sales performance
        Map<Product, Integer> salesPerformance = calculateSalesPerformance(products);

        // Calculate pricing analysis
        Map<Product, BigDecimal> pricingAnalysis = calculatePricingAnalysis(products);

        // Create and return the product report
        return new ProductReport(inventoryStatus, salesPerformance, pricingAnalysis);
    }

    // private BigDecimal calculateTotalRevenue(List<Sale> sales) {
    //     BigDecimal totalRevenue = BigDecimal.ZERO;
    //     for (Sale sale : sales) {
    //         totalRevenue = totalRevenue.add(sale.getTotal());
    //     }
    //     return totalRevenue;
    // }

    private List<Product> calculateTopSellingProducts(List<Sale> sales) {
        // Count occurrences of each product in all sales
        Map<Product, Integer> productSaleCount = new HashMap<>();
        for (Sale sale : sales) {
            for (Transaction transaction : sale.getTransactions()) {
                Product product = transaction.getProduct();
                productSaleCount.put(product, productSaleCount.getOrDefault(product, 0) + transaction.getQuantity());
            }
        }

        // Sort products by sale count in descending order
        List<Map.Entry<Product, Integer>> sortedEntries = productSaleCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        // Extract top selling products
        List<Product> topSellingProducts = new ArrayList<>();
        for (Map.Entry<Product, Integer> entry : sortedEntries) {
            topSellingProducts.add(entry.getKey());
            // You can limit the number of top selling products if needed
            if (topSellingProducts.size() >= 10) {
                break;
            }
        }

        return topSellingProducts;
    }

    private List<Seller> calculateTopPerformingSellers(List<Sale> sales) {
        // Calculate revenue generated by each seller
        Map<Seller, BigDecimal> sellerRevenue = new HashMap<>();
        for (Sale sale : sales) {
            Seller seller = sale.getSeller();
            BigDecimal saleTotal = sale.getTotal();
            sellerRevenue.put(seller, sellerRevenue.getOrDefault(seller, BigDecimal.ZERO).add(saleTotal));
        }

        // Sort sellers by revenue generated in descending order
        List<Map.Entry<Seller, BigDecimal>> sortedEntries = sellerRevenue.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toList());

        // Extract top performing sellers
        List<Seller> topPerformingSellers = new ArrayList<>();
        for (Map.Entry<Seller, BigDecimal> entry : sortedEntries) {
            topPerformingSellers.add(entry.getKey());
            // You can limit the number of top performing sellers if needed
            if (topPerformingSellers.size() >= 10) {
                break;
            }
        }

        return topPerformingSellers;
    }

    private List<Client> calculateTopSpendingClients(List<Client> clients) {
        // Calculate total spending by each client
        Map<Client, BigDecimal> clientTotalSpending = new HashMap<>();
        for (Client client : clients) {
            BigDecimal totalSpending = calculateTotalSpendingForClient(client);
            clientTotalSpending.put(client, totalSpending);
        }

        // Sort clients by total spending in descending order
        List<Client> sortedClients = clientTotalSpending.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Extract top spending clients
        List<Client> topSpendingClients = new ArrayList<>();
        for (Client client : sortedClients) {
            topSpendingClients.add(client);
            // You can limit the number of top spending clients if needed
            if (topSpendingClients.size() >= 10) {
                break;
            }
        }

        return topSpendingClients;
    }

    private Map<Client, Integer> calculateClientActivity(List<Client> clients) {
        Map<Client, Integer> clientActivity = new HashMap<>();

        // Iterate through each client
        for (Client client : clients) {
            // Calculate the total number of purchases for the client
            int totalPurchases = calculateTotalPurchasesForClient(client);
            clientActivity.put(client, totalPurchases);
        }

        return clientActivity;
    }

    private Map<String, Integer> calculateClientLocationStatistics(List<Client> clients) {
        Map<String, Integer> locationStatistics = new HashMap<>();

        // Count the number of clients in each location
        for (Client client : clients) {
            String location = client.getAddress();
            locationStatistics.put(location, locationStatistics.getOrDefault(location, 0) + 1);
        }

        return locationStatistics;
    }

    private int calculateInventoryStatus(List<Product> products) {
        int totalQuantity = 0;

        // Sum up the quantity of each product
        for (Product product : products) {
            totalQuantity += product.getQuantity();
        }

        return totalQuantity;
    }

    private Map<Product, Integer> calculateSalesPerformance(List<Product> products) {
        Map<Product, Integer> salesPerformance = new HashMap<>();

        // Initialize sales count for each product
        for (Product product : products) {
            salesPerformance.put(product, 0);
        }

        // Iterate through sales and increment sales count for each product
        for (Sale sale : saleService.getAllSales()) {
            for (Transaction transaction : sale.getTransactions()) {
                Product product = transaction.getProduct();
                int currentSalesCount = salesPerformance.get(product);
                salesPerformance.put(product, currentSalesCount + 1);
            }
        }

        return salesPerformance;
    }

    private Map<Product, BigDecimal> calculatePricingAnalysis(List<Product> products) {
        Map<Product, BigDecimal> pricingAnalysis = new HashMap<>();

        // Calculate average price for each product
        for (Product product : products) {
            BigDecimal averagePrice = calculateAveragePriceForProduct(product);
            pricingAnalysis.put(product, averagePrice);
        }

        return pricingAnalysis;
    }

    private BigDecimal calculateTotalSpendingForClient(Client client) {
        BigDecimal totalSpending = BigDecimal.ZERO;

        // Iterate through the sales associated with the client
        for (Sale sale : client.getSales()) {
            totalSpending = totalSpending.add(sale.getTotal());
        }

        return totalSpending;
    }

    private int calculateTotalPurchasesForClient(Client client) {
        // Initialize total purchases for the client
        int totalPurchases = 0;

        // Iterate through the sales associated with the client
        for (Sale sale : client.getSales()) {
            // Increment total purchases by the number of transactions in each sale
            totalPurchases += sale.getTransactions().size();
        }

        return totalPurchases;
    }

    private BigDecimal calculateAveragePriceForProduct(Product product) {
        List<BigDecimal> prices = new ArrayList<>();

        // Collect prices of all transactions for the product
        for (Sale sale : product.getSales()) {
            for (Transaction transaction : sale.getTransactions()) {
                if (transaction.getProduct().equals(product)) {
                    prices.add(transaction.getPrice());
                }
            }
        }

        // Calculate average price
        if (!prices.isEmpty()) {
            BigDecimal sum = prices.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
            return sum.divide(BigDecimal.valueOf(prices.size()), 2, BigDecimal.ROUND_HALF_UP);
        } else {
            return BigDecimal.ZERO;
        }
    }
}
