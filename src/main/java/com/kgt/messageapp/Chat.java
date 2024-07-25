package com.kgt.messageapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Chat implements Initializable {

    @FXML
    private Pane ChatBox;


    @FXML
    private TextField ChatInput;


    @FXML
    protected void sendMessageButton() {
        String text = ChatInput.getText();
        createChatMessage(text, ChatBox);
        MainManager.getInstance().getClient().SendMessageToServer(text);
    }


    @FXML
    public void DisplayMessage(String text, Pane ChatBox) {
        createChatMessage(text, ChatBox);
    }

    public Pane getChatBox() {
        return ChatBox;
    }


    private void createChatMessage(String text, Pane chatBox) {
        TextField newMessage = new TextField(text);
        newMessage.setEditable(false);
        newMessage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Chat.css")).toExternalForm());
        newMessage.getStyleClass().add("text-field-with-margin");


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ((VBox) SceneController.getScene().getRoot()).getChildren().add(newMessage);
            }
        });

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing Chat");
    }
}