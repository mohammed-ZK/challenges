package com.challenges.challengeTwo.Controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenges.challengeTwo.Entity.Sale;
import com.challenges.challengeTwo.Repository.Impl.SaleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @PutMapping("/{saleId}/transactions/{transactionId}")
    public void updateTransaction(
            @PathVariable Long saleId,
            @PathVariable Long transactionId,
            @RequestParam int quantity,
            @RequestParam BigDecimal price) {
        saleService.updateTransaction(saleId, transactionId, quantity, price);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Sale Sale = saleService.getSaleById(id);
        return ResponseEntity.ok(Sale);
    }

    @PostMapping
    public ResponseEntity<Sale> createSale(@Valid @RequestBody Sale Sale) {
        Sale createdSale = saleService.createSale(Sale);
        return new ResponseEntity<>(createdSale, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @Valid @RequestBody Sale Sale) {
        Sale updatedSale = saleService.updateSale(id, Sale);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return ResponseEntity.noContent().build();
    }
}
