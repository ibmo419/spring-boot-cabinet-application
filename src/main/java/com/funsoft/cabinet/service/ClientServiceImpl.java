package com.funsoft.cabinet.service;

import com.funsoft.cabinet.model.Client;
import com.funsoft.cabinet.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {


    @Autowired
    ClientRepository agent;

    @Override
    public void save_or_update(Client client) {
     agent.save(client);
    }

    @Override
    public List<Client> consulte() {
        return (List<Client>) agent.findAll() ;
    }

    @Override
    public void delete(long id) {
      agent.deleteById(id);
    }

    @Override
    public Client findById(long id) {
        return agent.findById(id).get();
    }
}
