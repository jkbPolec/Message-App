package com.kgt.messageapp;

import java.io.*;
import java.net.*;

public class Client {
    private Socket socket = null;
    private DataOutputStream outputStream = null;
    private DataInputStream inputStream = null;
    private DataInputStream serverInput = null;

    public Client(String address, int port) {
        try {
            socket = new Socket(address, port);
            System.out.println("Connected to " + address + ":" + port);

            inputStream = new DataInputStream(System.in);
            serverInput = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            new Thread(new ServerListener()).start();

            String line = "";
            while (!line.equals("Over")) {
                try {
                    line = inputStream.readLine();
                    outputStream.writeUTF(line);
                } catch (IOException i) {
                    System.out.println(i);
                }
            }
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException i) {
                System.out.println(i);
            }
        }
    }

    private class ServerListener implements Runnable {
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
//
//    public static void main(String[] args) {
//        Client client = new Client("127.0.0.1", 5000);
//    }

}

