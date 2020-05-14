package model;

import model.enums.CardValue;
import model.enums.SuitType;
import model.interfaces.ICard;

public class Card implements ICard {
    private CardValue cardValue;
    private SuitType suitType;
    private boolean watchable;

    public Card(CardValue cardValue, SuitType suitType) {
        this.cardValue = cardValue;
        this.suitType = suitType;
    }

    public Card() {
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public SuitType getSuitType() {
        return suitType;
    }

    public boolean isWatchable() {
        return watchable;
    }

    public void setWatchable(boolean watchable) {
        this.watchable = watchable;
    }

    @Override
    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    @Override
    public void setSuitType(SuitType suitType) {
        this.suitType = suitType;
    }

    @Override
    public String toString() {
        return cardValue.name().toLowerCase() + " of " + suitType.name().toLowerCase();
    }
}
