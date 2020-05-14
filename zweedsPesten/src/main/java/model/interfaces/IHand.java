package model.interfaces;

import model.Card;

import java.util.List;

public interface IHand {
    void addCards(List<ICard> cards);
    boolean placeCards(List<ICard> cards);
    boolean placeCard(ICard card);
    void addCard(ICard card);
    IPlayer getPlayer();
    List<ICard> getCards();
    IPlayedCards getPlayedCards();

    void returnCardsToDeck(IDeck deck);
}
