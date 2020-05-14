package model.algorithm;

import model.interfaces.ICard;
import model.interfaces.IPlayedCards;

import java.util.List;

public class MoveChecker implements IMoveChecker {

    private IPlayedCards playedCards;

    public MoveChecker(IPlayedCards playedCards) {
        this.playedCards = playedCards;
    }

    public boolean checkMultipleCards(List<ICard> cards) {
        boolean result = false;
        if (!cards.isEmpty()) {
            int playingCardValue = cards.get(0).getCardValue().getValue();
            if(cards.size() > 1) {
                if (checkValidPlayingCards(cards)) {
                    result = checkSingleCard(playingCardValue);
                }
            }
            else {
                result = checkSingleCard(playingCardValue);
            }
        }
        return result;
    }

    public boolean checkSingleCard(int playingCardValue) {
        boolean result = false;
        if (playingCardValue == 2 || playingCardValue == 3 || playingCardValue == 10) {
            result = true;
        } else {
            result = compareCards(playingCardValue, -1);
        }
        return result;
    }

    private boolean checkValidPlayingCards(List<ICard> cards) {
        boolean result = true;
        int prevCardValue = -1;
        for (ICard card : cards) {
            if (prevCardValue == -1) {
                prevCardValue = card.getCardValue().getValue();
            }
            else if(prevCardValue != card.getCardValue().getValue()) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean compareCards(int playingCardValue, int lastPlayedCardValue) {
        boolean result = false;
        if (!playedCards.getCards().isEmpty()) {
            if(lastPlayedCardValue == -1) {
                lastPlayedCardValue = playedCards.getCards().get(playedCards.getCards().size() - 1).getCardValue().getValue();
            }
            switch (lastPlayedCardValue) {
                case 3:
                    if (playedCards.getCards().size() > 1) {
                        int copyValue = playedCards.getCards().get(playedCards.getCards().size() - 2).getCardValue().getValue();
                        if(copyValue != 3) {
                            result = compareCards(playingCardValue, copyValue);
                        }
                        else if (copyValue <= playingCardValue) {
                            result = true;
                        }
                    } else if (lastPlayedCardValue <= playingCardValue) {
                        result = true;
                    }
                    break;
                case 7:
                    if (lastPlayedCardValue >= playingCardValue) {
                        result = true;
                    }
                    break;
                default:
                    if (lastPlayedCardValue <= playingCardValue) {
                        result = true;
                    }
                    break;
            }
        } else {
            result = true;
        }
        return result;
    }


}
