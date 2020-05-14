package model.interfaces;

import java.util.List;

public interface IGame {
    void loginPlayer(String sessionId, String userName, String password);

    void registerNewPlayer(String sessionId, String username, String password);

    void processClientPlaceCardsFromHand(String sessionId, List<ICard> cards);

    void processClientDrawFromDeck(String sessionId, int amount);

    void processClientPlaceCardsFromExtraHand(String sessionId, List<ICard> cards);

    void processClientDrawFromPlayedCards(String sessionId);

    void processClientDisconnect(String sessionId);

    int getNumberOfPlayers();

    void readyPlayer(String sessionId, boolean ready);

    void getAllPlayersInGame();
}
