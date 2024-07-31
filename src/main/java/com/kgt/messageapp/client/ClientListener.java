package com.kgt.messageapp.client;

import com.kgt.messageapp.MainManager;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.IOException;

public class ClientListener extends Thread {

    private DataInputStream serverInput;

    private TextArea ChatArea;

    public ClientListener(DataInputStream stream) throws IOException {
        this.serverInput = stream;
    };

    @Override
    public void run() {
        while (true) {
            try {
                String line = serverInput.readUTF();
                //MainManager.getInstance().getChatController().DisplayMessage(line, vbox);
                MainManager.getInstance().MessageReceived(line);
            } catch (IOException i) {
                System.out.println(i.getMessage());
                break;
            }
        }
    }
}