package websocketclient.interfaces;

import javafx.scene.input.MouseEvent;
import model.enums.GameAction;
import model.interfaces.ICard;

import java.util.List;
import java.util.Map;

public interface IGameClientGUI {

    void processGameSessionStarted();

    void processGameSessionResult(String winner);

    void processReadyResult(Map<String, Boolean> map);

    void processGetPlayerNames(List<String> playerNames);

    void setPlayerName(String playerName);

    void processExtraHandClicked(MouseEvent mouseEvent, String id, ICard card);
    void processHandClicked(MouseEvent mouseEvent, String id, ICard card);
    void processDeckClicked(MouseEvent mouseEvent, String id, ICard card);
    void processPlayedCardsClicked(MouseEvent mouseEvent, String id, ICard card);

    void processPlaceCardFromHand(ICard card);

    void processPlaceCardsFromHand(String playerName, List<ICard> cards);

    void processPlaceCardsFromExtraHand(String playerName, List<ICard> cards);

    void handlePlaceCardsFromHand(List<ICard> cards);

    void handlePlaceCardFromHand(ICard card);

    void processDrawCardsFromDeck(String playerName, List<ICard> cards);

    void processDrawCardsFromPlayedCards(String playerName, List<ICard> cards);

    void processInvalidMove(GameAction gameAction);

    void processDeckCreated(List<ICard> cards);

    void processExtraHandCreated(String playerName, List<ICard> cards);
}
