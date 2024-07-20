package com.kgt.messageapp;

import com.kgt.messageapp.client.Client;

public final class MainManager {
    private static volatile MainManager instance;
    private Client client;
    private Server server;


    private SceneController sceneController;

    private MainManager() {

    }


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
    }

    private enum AppModeType{
        CLIENT, SERVER;
    }

    private static AppModeType AppMode;


    public void SetAppMode(String type){
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




}
