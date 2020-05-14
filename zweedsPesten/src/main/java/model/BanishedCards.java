package model;

import model.interfaces.IBanishedCards;
import model.interfaces.ICard;

import java.util.ArrayList;
import java.util.List;

public class BanishedCards implements IBanishedCards {
    List<ICard> cards;

    public BanishedCards() {
        this.cards = new ArrayList<>();
    }

    public void addCards(List<ICard> cards)
    {
        this.cards.addAll(cards);
    }

    public List<ICard> getCards() {
        return cards;
    }
}
