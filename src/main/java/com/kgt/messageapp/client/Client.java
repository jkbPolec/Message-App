package com.kgt.messageapp.client;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream serverInput;
    private Thread thread;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to " + address + ":" + port);

            serverInput = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            thread = new Thread(new ServerListener());
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void CloseStreams()
    {
        try {
            this.getOutputStream().close();
            this.getSocket().close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }


    public class ServerListener implements Runnable {
        @Override
        public void run() {
            String line = "";
            while (true) {
                try {
                    line = serverInput.readUTF();
                    System.out.println(line);
                } catch (IOException i) {
                    System.out.println(i);
                    break;
                }
            }
        }
    }


    public void SendMessageToServer(String message) {
        try {
            this.getOutputStream().writeUTF(message);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
    }

    public Socket getSocket() {
        return socket;
    }

    public DataOutputStream getOutputStream() {
        return outputStream;
    }


    public DataInputStream getServerInput() {
        return serverInput;
    }
}

