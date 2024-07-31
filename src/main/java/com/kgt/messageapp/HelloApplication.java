package com.kgt.messageapp;

import com.kgt.messageapp.client.Client;
import com.kgt.messageapp.client.ClientListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        SceneController.SetStage(stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


        MainManager manager = MainManager.getInstance();
        manager.OnAppStart();

    }

    public static void main(String[] args) throws IOException {
        launch();

    }

    public static void StartClientThread(Client client)
    {
        try {
            ClientListener listener = new ClientListener(client.getServerInput());
            listener.start();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }


    }


}