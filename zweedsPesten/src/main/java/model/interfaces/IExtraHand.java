package model.interfaces;

import model.Card;

import java.util.List;

public interface IExtraHand {
    void addCards(List<ICard> cards);
    IPlayer getPlayer();
    void switchCard(IHand hand, Card card, int place);
    boolean placeCards(List<ICard> cards);
    boolean placeCard(ICard card);
    void returnCardsToDeck(IDeck deck);
    List<ICard> getCards();
}
