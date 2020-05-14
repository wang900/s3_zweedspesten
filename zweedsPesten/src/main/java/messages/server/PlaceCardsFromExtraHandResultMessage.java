package messages.server;


import model.interfaces.ICard;

import java.util.List;

public class PlaceCardsFromExtraHandResultMessage {

    private String playerName;
    private List<ICard> cards;

    public List<ICard> getCards() {
        return cards;
    }

    public String getPlayerName() {
        return playerName;
    }

    public PlaceCardsFromExtraHandResultMessage(String playerName, List<ICard> cards)
    {
        this.playerName = playerName;
        this.cards = cards;
    }
}
