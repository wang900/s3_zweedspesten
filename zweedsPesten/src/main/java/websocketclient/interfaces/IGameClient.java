package websocketclient.interfaces;


import model.Card;
import model.enums.GameAction;
import model.interfaces.ICard;

import java.util.List;
import java.util.Map;

public interface IGameClient {
        void registerPlayer(String userName, String password);

        void loginPlayer(String userName, String password);

        void handlePlayerRegistrationResponse(boolean success);

        void registerRegisterClientGUI(IRegisterClientGUI gui);

        void registerLoginClientGUI(ILoginClientGUI gui);

        void registerGameClientGUI(IGameClientGUI gui);

        void processGameSessionStarted();

        void handlePlayerRegistered(String playerName);

        void processGameSessionResult(String winner);

        void handleLoginResponse(String token, String playerName);

        void readyPlayer(boolean ready);

        void processReadyResult(Map<String, Boolean> map);

        void getAllPlayerNames();

        void handleGetPlayerNamesResponse(List<String> playerNames);

        void handlePlaceCardFromHand(ICard card);

        void handlePlaceCardsFromHand(List<ICard> cards);

        void processPlaceCardsFromHand(String playerName, List<ICard> cards);

        void processPlaceCardsFromExtraHand(String playerName, List<ICard> cards);

        void processDrawCardsFromDeck(String playerName, List<ICard> cards);

        void processDrawCardsFromPlayedCardsResult(String playerName, List<ICard> cards);

        void processInvalidMove(String sessionId, GameAction gameAction);

        void processDeckCreated(List<ICard> cards);

        void processExtraHandCreated(String playerName, List<ICard> cards);
}
