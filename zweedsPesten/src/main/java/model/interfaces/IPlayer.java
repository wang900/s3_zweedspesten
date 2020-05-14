package model.interfaces;

public interface IPlayer {
    String getUserName();
    String getPassword();
    String getSessionId();
    void setPassword(String password);
    boolean isReady();
    void setReady(boolean ready);
}
