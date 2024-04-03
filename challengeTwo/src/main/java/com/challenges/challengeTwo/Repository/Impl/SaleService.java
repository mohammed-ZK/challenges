package com.challenges.challengeTwo.Repository.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenges.challengeTwo.Entity.Sale;
import com.challenges.challengeTwo.Entity.Transaction;
import com.challenges.challengeTwo.Repository.Interfaces.SaleRepository;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;


        public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale createSale(Sale sale) {
        return saleRepository.save(sale);
    }

    public void updateTransaction(Long saleId, Long transactionId, int quantity, BigDecimal price) {
        Sale sale = saleRepository.findById(saleId).orElseThrow(() -> new RuntimeException("Sale not found"));
        Transaction transaction = sale.getTransactions().stream()
                .filter(t -> t.getId().equals(transactionId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
        transaction.setQuantity(quantity);
        transaction.setPrice(price);
    }


    public Sale getSaleById(Long id) {
        Optional<Sale> optionalSale = saleRepository.findById(id);
        return optionalSale.orElse(null);
    }

    public Sale updateSale(Long id, Sale Sale) {
        return saleRepository.save(Sale);
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
