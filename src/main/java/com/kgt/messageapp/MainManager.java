package com.kgt.messageapp;

import com.kgt.messageapp.client.Client;
import javafx.application.Platform;

public final class MainManager {

    private static volatile MainManager instance;

    private Client client;
    private Server server;


    private SceneController sceneController;

    public Chat getChatController() {
        return chatController;
    }

    private Chat chatController;

    private MainManager() {}


    public static MainManager getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (MainManager.class) {
            if (instance == null) {
                instance = new MainManager();
            }
            return instance;
        }
    }


    public void OnAppStart() {
        sceneController = new SceneController();
        chatController = new Chat();
    }

    public void MessageReceived(String msg) {
        System.out.println("Message Received: " + msg);
        chatController.DisplayMessage("TEST");
    }



    private enum AppModeType{
        CLIENT, SERVER;
    }

    private static AppModeType AppMode;

    private void SetAppMode(String type){
        if(type.equalsIgnoreCase("client")){
            AppMode = AppModeType.CLIENT;
        }
        else if(type.equalsIgnoreCase("server")){
            AppMode = AppModeType.SERVER;
        }
        else {
            throw new IllegalArgumentException("Unknown AppMode: " + type);
        }
    }

    public SceneController getSceneController() {
        return sceneController;
    }



    public void SetUpForServer() {
        this.server = new Server(5000);
        SetAppMode("server");
    }

    public void SetUpForClient(String address) {
        this.client = new Client(address, 5000);
        SetAppMode("client");

        try {
            sceneController.switchToClientScene();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        HelloApplication.StartClientThread(this.client);
    }

    public Client getClient() {
        return client;
    }



}
