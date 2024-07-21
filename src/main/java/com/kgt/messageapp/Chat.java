package com.kgt.messageapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Chat {

    private static Chat instance;

    @FXML
    private VBox ChatBox;

    @FXML
    private TextField ChatInput;

    public static Chat getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new Chat();
        return instance;
    }


    @FXML
    protected void sendMessageButton() {
        String text = ChatInput.getText();
        createChatMessage(text);
        MainManager.getInstance().getClient().SendMessageToServer(text);
    }

    @FXML
    public void DisplayMessage(String text) {
        createChatMessage(text);
    }

    @FXML
    private void createChatMessage(String text) {
        TextField newMessage = new TextField(text);
        newMessage.setEditable(false);
        newMessage.getStylesheets().add(getClass().getResource("Chat.css").toExternalForm());
        newMessage.getStyleClass().add("text-field-with-margin");

        ChatBox.getChildren().add(newMessage);
    }


}
