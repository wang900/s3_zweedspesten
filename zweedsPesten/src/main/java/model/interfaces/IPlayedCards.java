package model.interfaces;

import model.Card;

import java.util.List;

public interface IPlayedCards {

    List<ICard> getCards();
    void addCards(List<ICard> cards);
    void addCard(ICard card);
    List<ICard> drawCards(IHand hand);
}
