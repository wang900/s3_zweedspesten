package model.interfaces;

import model.Card;

import java.util.List;

public interface IDeck {

    List<ICard> drawCards(IHand hand, int amount);
    boolean createDeck();
    ICard placeCard();
    boolean reshuffleDeck();
    List<ICard> getCards();
    void drawCardsForExtraHand(IExtraHand extraHand);
    void addCards(List<ICard> cards);
}
