package websocketserver;

import messages.server.StartGameSessionMessage;
import messages.server.*;
import model.enums.GameAction;
import model.interfaces.ICard;
import websocketserver.interfaces.IServerMessageGenerator;
import websocketserver.interfaces.IServerWebSocket;

import java.util.List;
import java.util.Map;

public class ServerMessageGenerator implements IServerMessageGenerator {

    private IServerWebSocket serverSocket;

    public ServerMessageGenerator(IServerWebSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void notifyPlayerAdded(String sessionId, String playerName) {
        PlayerHasRegisteredMessage msg = new PlayerHasRegisteredMessage(playerName);
        serverSocket.sendToOthers(sessionId, msg);
    }

    public void notifyRegisterResult(String sessionId, boolean success)
    {
        RegistrationResultMessage msg = new RegistrationResultMessage(success);
        serverSocket.sendTo(sessionId, msg);
    }

    public void notifyLoginResult(String sessionId, String token, String playerName)
    {
        LoginResultMessage msg = new LoginResultMessage(token, playerName);
        serverSocket.sendTo(sessionId, msg);
    }

    public void notifyReadyPlayers(Map<String, Boolean> map) {
        ReadyResultMessage msg = new ReadyResultMessage(map);
        serverSocket.broadcast(msg);
    }

    @Override
    public void notifyPlayerNames(List<String> playerNames) {
        GetAllPlayerNamesResultMessage msg = new GetAllPlayerNamesResultMessage(playerNames);
        serverSocket.broadcast(msg);
    }

    @Override
    public void notifyPlayerDrawCardsFromDeck(String playerName, List<ICard> cards) {
        DrawFromDeckResultMessage msg = new DrawFromDeckResultMessage(playerName, cards);
        serverSocket.broadcast(msg);
    }

    @Override
    public void notifyPlayerDrawCardsFromPlayedCards(String playerName, List<ICard> cards) {
        DrawFromPlayedCardsResultMessage msg = new DrawFromPlayedCardsResultMessage(playerName, cards);
        serverSocket.broadcast(msg);
    }

    @Override
    public void notifyPlayerPlaceCardsFromHand(String playerName, List<ICard> cards) {
        PlaceCardsFromHandResultMessage msg = new PlaceCardsFromHandResultMessage(playerName, cards);
        serverSocket.broadcast(msg);
    }

    @Override
    public void notifyPlayerPlaceCardsFromExtraHand(String playerName, List<ICard> cards) {
        PlaceCardsFromExtraHandResultMessage msg = new PlaceCardsFromExtraHandResultMessage(playerName, cards);
        serverSocket.broadcast(msg);
    }

    @Override
    public void notifyPlayerInvalidMove(String sessionId, GameAction gameAction) {
        InvalidMoveMessage msg = new InvalidMoveMessage(gameAction);
        serverSocket.sendTo(sessionId, msg);
    }

    @Override
    public void notifySwitchTurn(String playerName) {
        SwitchTurnMessage msg = new SwitchTurnMessage(playerName);
        serverSocket.broadcast(msg);
    }

    @Override
    public void notifyPlayerCreateExtraHand(String playerName, List<ICard> cards) {
        ExtraHandCreatedMessage msg = new ExtraHandCreatedMessage(playerName, cards);
        serverSocket.broadcast(msg);
    }

    @Override
    public void notifyDeckCreated(List<ICard> cards) {
        DeckCreatedMessage msg = new DeckCreatedMessage(cards);
        serverSocket.broadcast(msg);
    }

    public void notifyStartGameSession()
    {
        serverSocket.broadcast(new StartGameSessionMessage());
    }

    public void notifyGameSessionEnded(String winner)
    {
        serverSocket.broadcast(new RoundResultMessage(winner));
    }


}
