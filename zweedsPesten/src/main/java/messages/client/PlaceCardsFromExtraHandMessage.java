package messages.client;


import model.interfaces.ICard;

import java.util.List;

public class PlaceCardsFromExtraHandMessage {

    private List<ICard> cards;

    public List<ICard> getCards() {
        return cards;
    }

    public PlaceCardsFromExtraHandMessage(List<ICard> cards)
    {
        this.cards = cards;
    }
}
