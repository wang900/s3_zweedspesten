package model;

import model.enums.CardValue;
import model.interfaces.IBanishedCards;
import model.interfaces.ICard;
import model.interfaces.IHand;
import model.interfaces.IPlayedCards;

import java.util.ArrayList;
import java.util.List;

public class PlayedCards implements IPlayedCards {

    private List<ICard> cards;
    private IBanishedCards banishedCards;

    public PlayedCards(IBanishedCards banishedCards) {
        this.cards = new ArrayList<>();
        this.banishedCards = banishedCards;
    }

    public void addCards(List<ICard> cards){
        this.cards.addAll(cards);
        for(ICard card : cards) {
            if(card.getCardValue().getValue() == 10) {
                banishCards();
            }
        }
    }

    public void addCard(ICard card){
        this.cards.add(card);
        if(card.getCardValue().getValue() == 10) {
            banishCards();
        }
    }

    public List<ICard> getCards() {
        return cards;
    }

    public List<ICard> drawCards(IHand hand) {
        List<ICard> temp = new ArrayList<>(cards);
        hand.addCards(temp);
        cards.clear();
        return temp;
    }

    private void banishCards() {
        banishedCards.addCards(cards);
        cards.clear();
    }
}
