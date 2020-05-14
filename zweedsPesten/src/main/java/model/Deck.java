package model;

import model.algorithm.DeckGenerator;
import model.algorithm.DeckShuffler;
import model.interfaces.ICard;
import model.interfaces.IDeck;
import model.interfaces.IExtraHand;
import model.interfaces.IHand;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Deck implements IDeck {

    private List<ICard> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public List<ICard> drawCards(IHand hand, int amount) {
        List<ICard> drawnCards = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            drawnCards.add(cards.get(0));
            drawnCards.get(i).setWatchable(true);
            cards.remove(0);
        }
        hand.addCards(drawnCards);
        return drawnCards;
    }

    public void drawCardsForExtraHand(IExtraHand extraHand){
        List<ICard> drawnCards = new ArrayList<>();
        for(int i = 0; i < 6; i++) {
            drawnCards.add(cards.get(0));
            drawnCards.get(i).setWatchable(true);
            cards.remove(0);
        }
        extraHand.addCards(drawnCards);
    }

    @Override
    public void addCards(List<ICard> cards) {
        this.cards.addAll(cards);
    }

    public boolean createDeck() {
        cards = DeckShuffler.shuffle(DeckGenerator.generateDeck());
        return true;
    }

    public ICard placeCard() {
        ICard temp = cards.get(0);
        cards.remove(0);
        return temp;
    }

    public boolean reshuffleDeck(){
        DeckShuffler.shuffle(cards);
        return true;
    }

    public List<ICard> getCards() {
        return cards;
    }
}
