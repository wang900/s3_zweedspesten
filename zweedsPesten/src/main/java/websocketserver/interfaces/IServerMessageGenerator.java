package websocketserver.interfaces;

import model.enums.GameAction;
import model.interfaces.ICard;
import model.interfaces.IDeck;
import model.interfaces.IHand;
import model.interfaces.IPlayedCards;

import java.util.List;
import java.util.Map;

public interface IServerMessageGenerator {

        void notifyPlayerAdded(String sessionId, String playerName);

        void notifyRegisterResult(String sessionId, boolean success);

        void notifyStartGameSession();

        void notifyGameSessionEnded(String winner);

        void notifyLoginResult(String sessionId, String token, String PlayerName);

        void notifyReadyPlayers(Map<String, Boolean> map);

        void notifyPlayerNames(List<String> playerNames);

        void notifyPlayerDrawCardsFromDeck(String playerName, List<ICard> cards);

        void notifyPlayerDrawCardsFromPlayedCards(String playerName, List<ICard> cards);

        void notifyPlayerPlaceCardsFromHand(String playerName, List<ICard> cards);

        void notifyPlayerPlaceCardsFromExtraHand(String playerName, List<ICard> cards);

        void notifyPlayerInvalidMove(String sessionId, GameAction gameAction);

        void notifySwitchTurn(String playerName);

        void notifyPlayerCreateExtraHand(String playerName, List<ICard> cards);

        void notifyDeckCreated(List<ICard> cards);
}
