package com.kgt.messageapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class HelloController {
    @FXML
    private Label testText;

    @FXML
    private RadioButton ServerRadio;

    @FXML
    private RadioButton ClientRadio;

    @FXML
    private TextField ClientName;

    @FXML
    private TextField ServerIp;


    @FXML
    protected void onStartButtonClick() {

        if (ServerRadio.isSelected()) {
            System.out.println("Server selected");
        }else
        {
            System.out.println("Client selected");
            System.out.println("Client name: " + ClientName.getText());
            System.out.println("Server ip: " + ServerIp.getText());
        }





        testText.setText("Started app");
    }
}