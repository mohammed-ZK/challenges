package com.challenges.challengeTwo.Repository.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenges.challengeTwo.Entity.Client;
import com.challenges.challengeTwo.Repository.Interfaces.ClientRepository;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.orElse(null);
    }

    public Client updateClient(Long id, Client client) {
        Client client2=clientRepository.findById(id).get();
        client2.setFirstName(client.getFirstName());
        client2.setLastName(client.getLastName());
        client2.setMobile(client.getMobile());
        client2.setEmail(client.getEmail());
        client2.setAddress(client.getAddress());
        return clientRepository.save(client2);
    }
    
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
