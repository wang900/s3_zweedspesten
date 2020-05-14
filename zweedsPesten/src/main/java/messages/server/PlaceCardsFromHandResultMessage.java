package messages.server;


import model.interfaces.ICard;

import java.util.List;

public class PlaceCardsFromHandResultMessage {

    private String playerName;

    private List<ICard> cards;

    public PlaceCardsFromHandResultMessage(String playerName, List<ICard> cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<ICard> getCards() {
        return cards;
    }
}
