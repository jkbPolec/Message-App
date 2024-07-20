package com.kgt.messageapp;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private ServerSocket server = null;
    private List<ClientHandler> clients = Collections.synchronizedList(new ArrayList<>());

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            while (true)
            {
                System.out.println("Waiting for client on port " + port);
                Socket socket = server.accept();
                System.out.println("Client connected");

                ClientHandler clientHandler = new ClientHandler(socket, this);
                clients.add(clientHandler);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            System.out.println("Server Error: " + e.getMessage());
        }
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
        while (!line.equals("End"))
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

