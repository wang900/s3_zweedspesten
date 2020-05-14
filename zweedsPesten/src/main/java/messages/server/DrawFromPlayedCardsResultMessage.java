package messages.server;

import model.interfaces.ICard;

import java.util.List;

public class DrawFromPlayedCardsResultMessage {

    private String playerName;

    public DrawFromPlayedCardsResultMessage(String playerName, List<ICard> cards) {
        this.cards = cards;
    }

    public List<ICard> getCards() {
        return cards;
    }

    private List<ICard> cards;

    public String getPlayerName() {
        return playerName;
    }
}
