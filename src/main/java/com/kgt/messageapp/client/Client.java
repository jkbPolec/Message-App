package com.kgt.messageapp.client;

import com.kgt.messageapp.MainManager;
import com.kgt.messageapp.Server;
import javafx.application.Platform;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket;
    private DataOutputStream outputStream;
    private DataInputStream serverInput;
    private Thread thread;

    public Client(String adress, int port) {
        try {
            socket = new Socket(adress, port);
            System.out.println("Connected to " + adress + ":" + port);

            serverInput = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            thread = new Thread(new ServerListener());
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void CloseStreams() {
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
            while (true) {
                try {
                    String line = serverInput.readUTF();
                    Platform.runLater(() -> MainManager.getInstance().MessageReceived(line));
                } catch (IOException i) {
                    System.out.println(i.getMessage());
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
    }

    public void SendMessageToServer(String message) {
        try {
            this.getOutputStream().writeUTF(message);
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
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

