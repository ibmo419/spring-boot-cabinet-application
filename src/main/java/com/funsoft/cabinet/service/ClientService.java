package com.funsoft.cabinet.service;

import com.funsoft.cabinet.model.Client;


import java.util.List;

public interface ClientService {
    public void save_or_update(Client client);
    public List<Client> consulte();
    public void delete(long id);
    public Client findById(long id);
}
