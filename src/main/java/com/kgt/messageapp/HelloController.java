package com.kgt.messageapp;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.event.ActionEvent;
import java.io.IOException;

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
    protected void onStartButtonClick(ActionEvent event) {

        if (ServerRadio.isSelected()) {
            System.out.println("Server selected");


        }else
        {
            System.out.println("Client selected");
            System.out.println("Client name: " + ClientName.getText());
            System.out.println("Server ip: " + ServerIp.getText());

            try{
                MainManager.getInstance().getSceneController().switchToClientScene(event);
            }
            catch (IOException e){
                System.out.println(e);
            }
        }



        testText.setText("Started app");
    }
}