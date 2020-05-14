package websocketclient.interfaces;

import model.Card;
import model.interfaces.ICard;

import java.util.List;

public interface IClientMessageGenerator {
    void registerPlayerOnServer(String name, String password);

    void login(String username, String password);

    void sendPlayerChangedCards(List<ICard> cards);

    void readyPlayer(boolean ready);

    void getAllPlayerNames();
}
