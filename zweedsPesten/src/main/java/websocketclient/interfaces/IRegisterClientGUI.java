package websocketclient.interfaces;

public interface IRegisterClientGUI {
    void processRegistrationResponse(boolean resp);

    void processPlayerRegistered(String playerName);
}
