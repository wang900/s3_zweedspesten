package model.algorithm;

import model.interfaces.ICard;

import java.util.List;

public interface IMoveChecker {
    boolean checkMultipleCards(List<ICard> cards);
    boolean checkSingleCard(int playingCardValue);
}
