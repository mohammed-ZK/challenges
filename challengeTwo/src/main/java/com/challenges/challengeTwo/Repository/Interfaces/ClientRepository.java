package com.challenges.challengeTwo.Repository.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.challenges.challengeTwo.Entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long>{
    
}
