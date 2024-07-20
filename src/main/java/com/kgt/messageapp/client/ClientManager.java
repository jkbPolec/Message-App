package com.kgt.messageapp.client;

import com.kgt.messageapp.Server;

import java.util.HashSet;
import java.util.Set;

public class ClientManager {

    private Server server;
    private Set<Client> clients;

    public ClientManager(Server server) {
        this.server = server;
        this.clients = new HashSet<>();
    }

    public void addClient(Client client) {
        if (!clients.contains(client)) {
            clients.add(client);
        }
        else
        {
            System.out.println("Client is already in the list");
            throw new IllegalArgumentException("Client is already in the list");
        }
    }

    public void createClient() {

        Client newClient = new Client("127.0.0.1", 5000);
        addClient(newClient);
    }


}




