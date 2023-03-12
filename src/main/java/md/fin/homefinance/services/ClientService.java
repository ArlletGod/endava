package md.fin.homefinance.services;


import md.fin.homefinance.model.Client;
import md.fin.homefinance.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findOne(Long id) {
        Optional<Client> foundItem = clientRepository.findById(id);
        return foundItem.orElse(null);
    }

    @Transactional
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Transactional
    public void update(Long id, Client updatedClient) {
        updatedClient.setId(id);
        clientRepository.save(updatedClient);
    }

    @Transactional
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}


