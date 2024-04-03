package com.challenges.challengeTwo.Report;

import java.util.List;
import java.util.Map;

import com.challenges.challengeTwo.Entity.Client;

public class ClientReport {
    private int totalClients;
    private List<Client> topSpendingClients;
    private Map<Client, Integer> clientActivity;
    private Map<String, Integer> clientLocationStatistics;

    // Constructor
    public ClientReport(int totalClients, List<Client> topSpendingClients, Map<Client, Integer> clientActivity, Map<String, Integer> clientLocationStatistics) {
        this.totalClients = totalClients;
        this.topSpendingClients = topSpendingClients;
        this.clientActivity = clientActivity;
        this.clientLocationStatistics = clientLocationStatistics;
    }

    // Getters and setters
    public int getTotalClients() {
        return totalClients;
    }

    public void setTotalClients(int totalClients) {
        this.totalClients = totalClients;
    }

    public List<Client> getTopSpendingClients() {
        return topSpendingClients;
    }

    public void setTopSpendingClients(List<Client> topSpendingClients) {
        this.topSpendingClients = topSpendingClients;
    }

    public Map<Client, Integer> getClientActivity() {
        return clientActivity;
    }

    public void setClientActivity(Map<Client, Integer> clientActivity) {
        this.clientActivity = clientActivity;
    }

    public Map<String, Integer> getClientLocationStatistics() {
        return clientLocationStatistics;
    }

    public void setClientLocationStatistics(Map<String, Integer> clientLocationStatistics) {
        this.clientLocationStatistics = clientLocationStatistics;
    }

}
