package websocketclient;

import model.enums.GameAction;
import model.interfaces.ICard;
import websocketclient.interfaces.*;

import java.util.List;
import java.util.Map;

public class GameClient implements IGameClient {

    IClientMessageGenerator messageGenerator;

    private IRegisterClientGUI registerClientGUI;
    private ILoginClientGUI loginClientGUI;
    private IGameClientGUI gameClientGUI;

    @Override
    public void registerRegisterClientGUI(IRegisterClientGUI gui) {
        this.registerClientGUI = gui;
    }

    @Override
    public void registerLoginClientGUI(ILoginClientGUI gui) {
        this.loginClientGUI = gui;
    }

    @Override
    public void registerGameClientGUI(IGameClientGUI gui) {
        this.gameClientGUI = gui;
    }

    public GameClient(IClientMessageGenerator generator)
    {
        this.messageGenerator = generator;
    }

    public void registerPlayer(String userName, String password)
    {
        messageGenerator.registerPlayerOnServer(userName, password);
    }

    public void loginPlayer(String userName, String password)
    {
        messageGenerator.login(userName, password);
    }

    public void handlePlayerRegistrationResponse(boolean success)
    {
        registerClientGUI.processRegistrationResponse(success);
    }



    public void handleLoginResponse(String token, String playerName)
    {
        loginClientGUI.processLoginResponse(token, playerName);
    }

    @Override
    public void readyPlayer(boolean ready) {
        messageGenerator.readyPlayer(ready);
    }

    public void processReadyResult(Map<String, Boolean> map) {
        gameClientGUI.processReadyResult(map);
    }

    public void getAllPlayerNames() {
        messageGenerator.getAllPlayerNames();
    }

    @Override
    public void handleGetPlayerNamesResponse(List<String> playerNames) {
        gameClientGUI.processGetPlayerNames(playerNames);
    }

    @Override
    public void handlePlaceCardFromHand(ICard card) {
        gameClientGUI.handlePlaceCardFromHand(card);
    }

    @Override
    public void handlePlaceCardsFromHand(List<ICard> cards) {
        gameClientGUI.handlePlaceCardsFromHand(cards);
    }

    @Override
    public void processPlaceCardsFromHand(String playerName, List<ICard> cards) {
        gameClientGUI.processPlaceCardsFromHand(playerName, cards);
    }

    @Override
    public void processPlaceCardsFromExtraHand(String playerName, List<ICard> cards) {
        gameClientGUI.processPlaceCardsFromExtraHand(playerName, cards);
    }

    @Override
    public void processDrawCardsFromDeck(String playerName, List<ICard> cards) {
        gameClientGUI.processDrawCardsFromDeck(playerName, cards);
    }

    @Override
    public void processDrawCardsFromPlayedCardsResult(String playerName, List<ICard> cards) {
        gameClientGUI.processDrawCardsFromPlayedCards(playerName, cards);
    }

    @Override
    public void processInvalidMove(String sessionId, GameAction gameAction) {
        gameClientGUI.processInvalidMove(gameAction);
    }

    @Override
    public void processDeckCreated(List<ICard> cards) {
        gameClientGUI.processDeckCreated(cards);
    }

    @Override
    public void processExtraHandCreated(String playerName, List<ICard> cards) {
        gameClientGUI.processExtraHandCreated(playerName, cards);
    }

    public void processGameSessionStarted()
    {
        gameClientGUI.processGameSessionStarted();
    }

    public void handlePlayerRegistered(String playerName)
    {
        registerClientGUI.processPlayerRegistered(playerName);
    }

    public void sendPlayerChangedCards(List<ICard> cards)
    {
        messageGenerator.sendPlayerChangedCards(cards);
    }

    public void processGameSessionResult(String winner)
    {
        gameClientGUI.processGameSessionResult(winner);
    }
}
