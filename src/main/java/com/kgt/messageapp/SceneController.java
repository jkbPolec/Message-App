package com.kgt.messageapp;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.util.Objects;

public class SceneController {

    private static Stage stage;

    public static Scene getScene() {
        return scene;
    }

    private static Scene scene;
    private Parent root;

    public static void SetStage(Stage s) {
        stage = s;
    }

    @FXML
    protected void switchToClientScene() throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("clientChat.fxml")));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
