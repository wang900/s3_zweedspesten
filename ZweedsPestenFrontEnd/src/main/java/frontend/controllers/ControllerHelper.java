package frontend.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logging.Logger;
import websocketclient.interfaces.IGameClient;
import websocketclient.interfaces.IGameClientGUI;

import java.io.IOException;
import java.lang.reflect.Constructor;

public class ControllerHelper {
    private static ControllerHelper instance = null;

    public static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }

    private IGameClient gameClient;
    private Stage primaryStage;
    private IGameClientGUI gameGUI;

    public void loadFxml(String title, String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

        /*
                USE REFLECTION FOR DEPENDENCY INJECTION:
                INJECT EVERY CONTROLLER INSTANCE, WHICH IS CREATED DYNAMICALLY WHILE LOADING THE FXML,
                WITH THE SAME GAME CLIENT INSTANCE
         */
        loader.setControllerFactory((Class<?> type) -> {
            try {
                // look for constructor taking MyService as a parameter
                for (Constructor<?> c : type.getConstructors()) {
                    if (c.getParameterCount() == 1 && c.getParameterTypes()[0]==IGameClient.class) {
                        return c.newInstance(gameClient);
                    }
                }
                // didn't find appropriate constructor, just use default constructor:
                return type.newInstance();
            } catch (Exception exc) {
                Logger.getInstance().log(exc);
                return null;
            }
        });


        Parent root = loader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle(title);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void setGameClient(IGameClient client)
    {
        if(this.gameClient== null)
            this.gameClient = client;
    }

    public void setPrimaryStage(Stage stage) {
        if(this.primaryStage == null) {
            this.primaryStage = stage;
        }
    }

    public IGameClientGUI getGameGUI() {
        return gameGUI;
    }

    public void setGameGUI(IGameClientGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
