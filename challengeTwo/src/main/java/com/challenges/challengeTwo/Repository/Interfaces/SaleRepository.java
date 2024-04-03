package com.challenges.challengeTwo.Repository.Interfaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenges.challengeTwo.Entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long>{

    List<Sale> findByCreationDateBetween(Date startDate, Date endDate);
    
}
