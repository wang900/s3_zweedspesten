package model;

import model.algorithm.IMoveChecker;
import model.algorithm.MoveChecker;
import model.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class Hand implements IHand {

    private IPlayer player;
    private List<ICard> cards;
    private IPlayedCards playedCards;
    private IMoveChecker moveChecker;

    public Hand(IPlayer player, IPlayedCards playedCards) {
        this.player = player;
        this.cards = new ArrayList<>();
        this.playedCards = playedCards;
        moveChecker = new MoveChecker(playedCards);
    }

    public void addCards(List<ICard> cards) {
        this.cards.addAll(cards);
    }

    public void addCard(ICard card) {
        this.cards.add(card);
    }

    public boolean placeCards(List<ICard> cards) {
        boolean result = moveChecker.checkMultipleCards(cards);
        if(result) {
            for (ICard card : cards) {
                this.cards.remove(card);
                playedCards.addCard(card);
            }
        }

        return result;
    }

    public boolean placeCard(ICard card) {
        boolean result = moveChecker.checkSingleCard(card.getCardValue().getValue());
        if(result) {
            this.cards.remove(card);
            playedCards.addCard(card);
        }

        return result;
    }

    public IPlayer getPlayer() {
        return player;
    }

    public List<ICard> getCards() {
        return cards;
    }

    public IPlayedCards getPlayedCards() {
        return playedCards;
    }

    @Override
    public void returnCardsToDeck(IDeck deck) {

    }
}
