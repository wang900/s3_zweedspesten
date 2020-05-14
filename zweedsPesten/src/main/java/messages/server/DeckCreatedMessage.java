package messages.server;

import model.interfaces.ICard;

import java.util.List;

public class DeckCreatedMessage {
    private List<ICard> cards;

    public DeckCreatedMessage(List<ICard> cards) {
        this.cards = cards;
    }

    public List<ICard> getCards() {
        return cards;
    }
}
