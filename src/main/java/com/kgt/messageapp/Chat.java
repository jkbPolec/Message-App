package com.kgt.messageapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class Chat {

    @FXML
    private VBox ChatBox;

    @FXML
    private TextField ChatInput;

    @FXML
    protected void addBoxTest() {

        String text = ChatInput.getText();
        TextField newMessage = new TextField(text);
        newMessage.setEditable(false);
        newMessage.getStylesheets().add(getClass().getResource("Chat.css").toExternalForm());
        newMessage.getStyleClass().add("text-field-with-margin");

        ChatBox.getChildren().add(newMessage);
    }

    @FXML
    protected void sendMessageButton() {
        String text = ChatInput.getText();
        createChatMessage(text);


    }

    private void createChatMessage(String text) {


    }


}
