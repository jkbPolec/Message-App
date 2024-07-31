package com.kgt.messageapp;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class Chat implements Initializable {

    @FXML
    private TextArea ChatArea;

    @FXML
    private TextField ChatInput;

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

    public Pane getChatBox() {
        return new VBox();
    }


    private void createChatMessage(String text) {
        TextField newMessage = new TextField(text);
        newMessage.setEditable(false);
        newMessage.getStylesheets().add(Objects.requireNonNull(getClass().getResource("Chat.css")).toExternalForm());
        newMessage.getStyleClass().add("text-field-with-margin");


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //((VBox) SceneController.getScene().getRoot()).getChildren().add(newMessage);
                String current = ChatArea.getText();
                ChatArea.setText(current + text + "\n");
            }
        });

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing Chat");
    }
}