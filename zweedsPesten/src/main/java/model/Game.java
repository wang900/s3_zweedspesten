package model;

import client.IAuthRestClient;
import eventhandlers.IObserver;
import eventhandlers.ISubject;
import model.enums.GameAction;
import model.enums.GameState;
import model.interfaces.ICard;
import model.interfaces.IGame;
import model.interfaces.IGameSession;
import model.interfaces.IPlayer;
import websocketserver.interfaces.IServerMessageGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game implements IGame, IObserver {

    private String name;
    private IServerMessageGenerator messageGenerator;
    private List<IPlayer> players = new ArrayList<>();

    private GameState gameState = GameState.WAITING_FOR_PLAYERS;

    private IGameSession currentGameSession;
    private List<IGameSession> passedGameSessions = new ArrayList<>();

    private IAuthRestClient restClient;

    public Game(IServerMessageGenerator messageGenerator, IAuthRestClient restClient) {
        this.messageGenerator = messageGenerator;
        this.restClient = restClient;
    }

    public GameState getGameState() {
        return gameState;
    }

    @Override
    public void loginPlayer(String sessionId, String userName, String password) {

        if (players.size() < 4) {
            if (checkPlayerNameAlreadyExists(userName)) {
                messageGenerator.notifyRegisterResult(sessionId, false);
                return;
            }
            String token = restClient.login(userName, password);
            messageGenerator.notifyLoginResult(sessionId, token, userName);
            if (token != null && !token.equals("")) {
                Player p = new Player(sessionId, userName);
                players.add(p);
                messageGenerator.notifyPlayerAdded(sessionId, userName);
            }
        } else {
            messageGenerator.notifyRegisterResult(sessionId, false);
        }
    }

    public void readyPlayer(String sessionId, boolean ready) {
        for (IPlayer p : players) {
            if (p.getSessionId().equals(sessionId)) {
                p.setReady(ready);
                break;
            }
        }
        checkStartingCondition();
    }

    public void getAllPlayersInGame() {
        List<String> playerNames = new ArrayList<>();
        for (IPlayer p : players) {
            playerNames.add(p.getUserName());
        }
        messageGenerator.notifyPlayerNames(playerNames);
    }

    private void checkStartingCondition() {

        int amountOfPlayers = players.size();
        int amountOfReadyPlayers = 0;
        if (amountOfPlayers >= 2) {
            for (IPlayer p : players) {
                if (p.isReady()) {
                    amountOfReadyPlayers++;
                }
            }
            //Start the game
            if (amountOfReadyPlayers == amountOfPlayers) {
                startNewGameSession();
            } else {
                notifyReadyPlayers();
            }
        }
    }

    private void notifyReadyPlayers() {
        Map<String, Boolean> map = new HashMap<>();
        for (IPlayer p : players) {
            map.put(p.getUserName(), p.isReady());
        }
        messageGenerator.notifyReadyPlayers(map);
    }

    private void startNewGameSession() {
        GameSession gameSession = new GameSession(players);
        gameSession.subscribeObserver(this);
        if (currentGameSession != null)
            passedGameSessions.add(currentGameSession);
        currentGameSession = gameSession;
        gameState = GameState.GAME_SESSION_ACTIVE;
        currentGameSession.start();
        messageGenerator.notifyStartGameSession();
    }

    private boolean checkPlayerNameAlreadyExists(String userName) {

        boolean result = false;

        for (IPlayer p : players)
            if (p.getUserName().equals(userName)) {
                result = true;
                break;
            }
        return result;
    }

    @Override
    public void registerNewPlayer(String sessionId, String username, String password) {
        boolean success = restClient.register(username, password);
        if (success)
            messageGenerator.notifyRegisterResult(sessionId, true);
        else {
            messageGenerator.notifyRegisterResult(sessionId, false);
        }
    }

    @Override
    public void processClientPlaceCardsFromHand(String sessionId, List<ICard> cards) {
        if (gameState == GameState.GAME_SESSION_ACTIVE) {
            currentGameSession.processClientPlaceCardsFromHand(sessionId, cards);
        }
    }

    @Override
    public void processClientDrawFromDeck(String sessionId, int amount) {
        if (gameState == GameState.GAME_SESSION_ACTIVE) {
            currentGameSession.processClientDrawFromDeck(sessionId, amount);
        }
    }

    @Override
    public void processClientPlaceCardsFromExtraHand(String sessionId, List<ICard> cards) {
        if (gameState == GameState.GAME_SESSION_ACTIVE) {
            currentGameSession.processClientPlaceCardsFromExtraHand(sessionId, cards);
        }
    }

    @Override
    public void processClientDrawFromPlayedCards(String sessionId) {
        if (gameState == GameState.GAME_SESSION_ACTIVE) {
            currentGameSession.processClientDrawFromPlayedCards(sessionId);
        }
    }

    @Override
    public void processClientDisconnect(String sessionId) {
        for (IPlayer p : players)
            if (p.getSessionId().equals(sessionId)) {
                currentGameSession.processClientDisconnect(p);
                players.remove(p);
            }

        if (gameState != GameState.WAITING_FOR_PLAYERS) {
            if (players.size() < 2) {
                currentGameSession = null;
                gameState = GameState.WAITING_FOR_PLAYERS;
            }
        }
    }

    @Override
    public int getNumberOfPlayers() {
        return players.size();
    }

    @Override
    public void Update(ISubject subject) {
        handleAction(currentGameSession.getGameAction());
    }

    private void handleAction(GameAction gameAction) {
        switch (gameAction) {
            case DRAW_CARDS_FROM_DECK:
                messageGenerator.notifyPlayerDrawCardsFromDeck(currentGameSession.getCurrentPlayer().getUserName(), currentGameSession.getChangedCards());
                break;
            case CREATE_DECK:
                messageGenerator.notifyDeckCreated(currentGameSession.getChangedCards());
                break;
            case CREATE_EXTRA_HAND:
                messageGenerator.notifyPlayerCreateExtraHand(currentGameSession.getCurrentPlayer().getUserName(), currentGameSession.getChangedCards());
                break;
            case DRAW_CARDS_FROM_PLAYED_CARDS:
                messageGenerator.notifyPlayerDrawCardsFromPlayedCards(currentGameSession.getCurrentPlayer().getUserName(), currentGameSession.getChangedCards());
                break;
            case PLACE_CARD_FROM_EXTRA_HAND_SUCCESS:
                messageGenerator.notifyPlayerPlaceCardsFromExtraHand(currentGameSession.getCurrentPlayer().getUserName(), currentGameSession.getChangedCards());
                break;
            case PLACE_CARD_FROM_HAND_SUCCESS:
                messageGenerator.notifyPlayerPlaceCardsFromHand(currentGameSession.getCurrentPlayer().getUserName(), currentGameSession.getChangedCards());
                break;
            case PLACE_CARD_FROM_HAND_FAILURE:
            case PLACE_CARD_FROM_EXTRA_HAND_FAILURE:
            case INVALID_TURN:
                messageGenerator.notifyPlayerInvalidMove(currentGameSession.getCurrentPlayer().getSessionId(), gameAction);
                break;
            case SWITCH_TURN:
                messageGenerator.notifySwitchTurn(currentGameSession.getCurrentPlayer().getSessionId());
                break;
        }
    }
}
