package model.interfaces;

import eventhandlers.ISubject;
import model.Card;
import model.enums.GameAction;

import java.util.List;

public interface IGameSession {
    void processClientPlaceCardsFromHand(String sessionId, List<ICard> cards);

    void processClientDisconnect(IPlayer player);

    void processClientDrawFromDeck(String sessionId, int amount);

    void processClientPlaceCardsFromExtraHand(String sessionId, List<ICard> cards);

    void processClientDrawFromPlayedCards(String sessionId);

    IPlayer getCurrentPlayer();

    GameAction getGameAction();

    List<ICard> getChangedCards();

    void start();
}
