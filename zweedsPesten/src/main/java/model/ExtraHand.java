package model;

import model.algorithm.IMoveChecker;
import model.algorithm.MoveChecker;
import model.interfaces.*;

import java.util.ArrayList;
import java.util.List;

public class ExtraHand implements IExtraHand {
    private IPlayer player;
    private List<ICard> cards;
    private IPlayedCards playedCards;
    private IMoveChecker moveChecker;

    public ExtraHand(IPlayer player, IPlayedCards playedCards) {
        this.player = player;
        this.cards = new ArrayList<>();
        this.playedCards = playedCards;
        this.moveChecker = new MoveChecker(playedCards);
    }

    public void addCards(List<ICard> cards) {
        for (int i = 0; i < cards.size(); i++) {
            this.cards.add(cards.get(i));
            if (i < 3) {
                this.cards.get(i).setWatchable(false);
            }
        }
    }

    //place is the spot the player clicks on the UI, it is always 0, 1 or 2.
    public void switchCard(IHand hand, Card card, int place) {
        if (place < 3) {
            int actualPlace = place + 3;
            ICard temp = cards.get(actualPlace);
            cards.set(actualPlace, card);
            hand.addCard(temp);
        }
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

    @Override
    public void returnCardsToDeck(IDeck deck) {
        deck.addCards(cards);
        cards.clear();
    }

    public IPlayer getPlayer() {
        return player;
    }

    public List<ICard> getCards() {
        return cards;
    }
}
