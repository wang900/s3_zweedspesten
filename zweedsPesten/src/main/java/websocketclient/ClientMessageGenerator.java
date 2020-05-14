package websocketclient;

import messages.client.*;
import model.interfaces.ICard;
import websocketclient.interfaces.IClientMessageGenerator;
import websocketclient.interfaces.IClientWebSocket;

import java.util.List;

public class ClientMessageGenerator implements IClientMessageGenerator {

    private IClientWebSocket clientSocket;

    public ClientMessageGenerator(IClientWebSocket clientSocket)
    {
        this.clientSocket = clientSocket;
    }

    public void registerPlayerOnServer(String name, String password)
    {
        clientSocket.send(new RegisterPlayerMessage(name, password));
    }

    public void login(String name, String password)
    {
        clientSocket.send(new LoginMessage(name, password));
    }

    public void sendPlayerChangedCards(List<ICard> cards)
    {
        PlaceCardsFromHandMessage msg = new PlaceCardsFromHandMessage(cards);
        clientSocket.send(msg);
    }

    public void readyPlayer(boolean ready) {
        clientSocket.send(new ReadyMessage(ready));
    }

    public void getAllPlayerNames() {
        clientSocket.send(new GetAllPlayerNamesMessage());
    }

}
