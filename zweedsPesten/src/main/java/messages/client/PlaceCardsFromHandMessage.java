package messages.client;


import model.Card;
import model.interfaces.ICard;

import java.util.List;

public class PlaceCardsFromHandMessage {

    private List<ICard> cards;

    public List<ICard> getCards() {
        return cards;
    }

    public PlaceCardsFromHandMessage(List<ICard> cards)
    {
        this.cards = cards;
    }
}
