package frontend.controllers;

import logging.Logger;
import websocketclient.interfaces.IGameClient;

public abstract class BaseController {
    private IGameClient gameClient;

    public IGameClient getGameClient() {
        return gameClient;
    }

    public BaseController(IGameClient gameClient)
    {
        this.gameClient = gameClient;
    }

    public void loadFxml(String title,String fxml)
    {
        try {
            ControllerHelper.getInstance().loadFxml(title, fxml);
        } catch (Exception e) {
            Logger.getInstance().log(e);
        }
    }
    public void setPlayerName(String playerName) {
        ControllerHelper.getInstance().getGameGUI().setPlayerName(playerName);
    }
}
