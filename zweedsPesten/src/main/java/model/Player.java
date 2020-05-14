package model;

import dataacces.Entity;
import model.interfaces.IPlayer;

public class Player extends Entity implements IPlayer {

    private String userName;
    private String password;
    private String sessionId;
    private boolean ready;

    public Player(String sessionId, String userName) {
        this.sessionId = sessionId;
        this.userName = userName;
    }


    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
