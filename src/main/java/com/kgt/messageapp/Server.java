package com.kgt.messageapp;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ServerSocket serverSocket = null;
    private List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started");

            ClientSearcher searcher = new ClientSearcher(this, serverSocket);
            new Thread(searcher).start();

        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    public void AddClient(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public void broadCastMessage(String message, ClientHandler excludeClient) {
        synchronized (clients) {
            for (ClientHandler client : clients) {
                if (client != excludeClient) {
                    client.sendMessage(message);
                }
            }
        }
    }


    public static void main(String[] args) {
        Server server = new Server(5000);
    }

}

class ClientSearcher implements Runnable {

    ServerSocket serverSocket;
    Server server;

    public ClientSearcher(Server server, ServerSocket serverSocket) {
        this.server = server;
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        while (true)
        {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                ClientHandler clientHandler = new ClientHandler(socket, server);
                server.AddClient(clientHandler);
                new Thread(clientHandler).start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class ClientHandler implements Runnable {
    private Socket socket;
    private Server server;
    private DataInputStream in;
    private DataOutputStream out;

    public ClientHandler(Socket socket, Server server) {
        this.server = server;
        this.socket = socket;

        try{
            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(  socket.getRemoteSocketAddress() + "" + message);
        } catch (IOException e) {
            System.out.println("Error sending message: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String line = "";
        while (true)
        {
            try {
                line = in.readUTF();
                System.out.println("Received: " + line);
                server.broadCastMessage(line, this);
            } catch (IOException e) {
                System.out.println("Server Error: " + e.getMessage());
                break;
            }
        }

        try {
            System.out.println("Closing connection");
            socket.close();
            in.close();
            out.close();
        } catch (IOException i) {
            System.out.println("Server Error: " + i.getMessage());
        }
    }


}

