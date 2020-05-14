package model;

import eventhandlers.Subject;
import model.enums.GameAction;
import model.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class GameSession extends Subject implements IGameSession {

    private IBanishedCards banishedCards;
    private IDeck deck;
    private IPlayedCards playedCards;
    private List<IHand> hands;
    private List<IExtraHand> extraHands;
    private List<IPlayer> players;
    private List<ICard> changedCards;
    private IPlayer currentPlayer;
    private GameAction gameAction;

    public GameSession(List<IPlayer> players) {
        this.banishedCards = new BanishedCards();
        this.hands = new ArrayList<>();
        this.extraHands = new ArrayList<>();
        this.deck = new Deck();
        this.playedCards = new PlayedCards(banishedCards);
        this.players = players;
    }

    @Override
    public void start() {
        createDeck();
        createExtraHands();
        createHands();
    }

    private void createDeck() {
        gameAction = GameAction.CREATE_DECK;
        deck.createDeck();
        changedCards = deck.getCards();
        notifyObservers(this);
    }

    @Override
    public GameAction getGameAction() {
        return gameAction;
    }

    @Override
    public List<ICard> getChangedCards() {
        return changedCards;
    }

    @Override
    public IPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    private void createHands() {
        gameAction = GameAction.DRAW_CARDS_FROM_DECK;
        for (IPlayer player : players) {
            currentPlayer = player;
            IHand hand = new Hand(player, playedCards);
            deck.drawCards(hand, 3);
            hands.add(hand);
            changedCards = hand.getCards();
            notifyObservers(this);
        }
    }

    private void createExtraHands() {
        gameAction = GameAction.CREATE_EXTRA_HAND;
        for (IPlayer player : players) {
            currentPlayer = player;
            IExtraHand extraHand = new ExtraHand(player, playedCards);
            deck.drawCardsForExtraHand(extraHand);
            extraHands.add(extraHand);
            changedCards = extraHand.getCards();
            notifyObservers(this);
        }
    }

    private IHand findHandBySessionId(String sessionId) {
        IHand temp = null;
        for (IHand hand : hands) {
            if (hand.getPlayer().getSessionId().equals(sessionId)) {
                temp = hand;
                break;
            }
        }
        return temp;
    }

    private IExtraHand findExtraHandBySessionId(String sessionId) {
        IExtraHand temp = null;
        for (IExtraHand extraHand : extraHands) {
            if (extraHand.getPlayer().getSessionId().equals(sessionId)) {
                temp = extraHand;
                break;
            }
        }
        return temp;
    }

    @Override
    public void processClientDisconnect(IPlayer player) {
        for (IHand hand : hands) {
            if (hand.getPlayer() == player) {
                hand.returnCardsToDeck(deck);
            }
        }
        for (IExtraHand extraHand : extraHands) {
            if (extraHand.getPlayer() == player) {
                extraHand.returnCardsToDeck(deck);
            }
        }
        players.remove(player);
    }

    public void processClientDrawFromDeck(String sessionId, int amount) {
        if (currentPlayer.getSessionId().equals(sessionId)) {
            IHand hand = findHandBySessionId(sessionId);
            gameAction = GameAction.DRAW_CARDS_FROM_DECK;
            changedCards = deck.drawCards(hand, amount);
        } else {
            gameAction = GameAction.INVALID_TURN;
        }
        notifyAndSwitchTurn();
    }

    public void processClientDrawFromPlayedCards(String sessionId) {
        if (currentPlayer.getSessionId().equals(sessionId)) {
            IHand hand = findHandBySessionId(sessionId);
            gameAction = GameAction.DRAW_CARDS_FROM_PLAYED_CARDS;
            changedCards = playedCards.drawCards(hand);
        } else {
            gameAction = GameAction.INVALID_TURN;
        }
        notifyAndSwitchTurn();
    }

    @Override
    public void processClientPlaceCardsFromHand(String sessionId, List<ICard> cards) {
        if (currentPlayer.getSessionId().equals(sessionId)) {
            IHand hand = findHandBySessionId(sessionId);
            if (hand.placeCards(cards)) {
                gameAction = GameAction.PLACE_CARD_FROM_HAND_SUCCESS;
                changedCards = cards;
            } else {
                gameAction = GameAction.PLACE_CARD_FROM_HAND_FAILURE;
            }
        } else {
            gameAction = GameAction.INVALID_TURN;
        }
        notifyAndSwitchTurn();
    }

    public void processClientPlaceCardsFromExtraHand(String sessionId, List<ICard> cards) {
        if((currentPlayer.getSessionId().equals(sessionId))) {
            if (findHandBySessionId(sessionId).getCards().isEmpty()) {
                IExtraHand extraHand = findExtraHandBySessionId(sessionId);
                if (extraHand.placeCards(cards)) {
                    gameAction = GameAction.PLACE_CARD_FROM_EXTRA_HAND_SUCCESS;
                    changedCards = cards;
                } else {
                    gameAction = GameAction.PLACE_CARD_FROM_EXTRA_HAND_FAILURE;
                }
            } else {
                gameAction = GameAction.PLACE_CARD_FROM_EXTRA_HAND_FAILURE;
            }
        } else {
            gameAction = GameAction.INVALID_TURN;
        }
        notifyAndSwitchTurn();
    }

    private void notifyAndSwitchTurn() {
        notifyObservers(this);
        if (!(GameAction.INVALID_TURN.equals(gameAction) || GameAction.PLACE_CARD_FROM_EXTRA_HAND_FAILURE.equals(gameAction) || GameAction.PLACE_CARD_FROM_HAND_FAILURE.equals(gameAction))) {
            int lastIndex = players.lastIndexOf(currentPlayer) + 1;
            if ((lastIndex > players.size())) {
                lastIndex = 0;
            }
            currentPlayer = players.get(lastIndex);
            notifyObservers(this);
        }

    }
}
