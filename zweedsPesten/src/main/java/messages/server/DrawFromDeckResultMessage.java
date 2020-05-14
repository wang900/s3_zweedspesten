package messages.server;

import model.interfaces.ICard;

import java.util.List;

public class DrawFromDeckResultMessage {

    private String playerName;

    private List<ICard> cards;

    public DrawFromDeckResultMessage(String playerName, List<ICard> cards) {
        this.playerName = playerName;
        this.cards = cards;
    }

    public List<ICard> getCards() {
        return cards;
    }

    public String getPlayerName() {
        return playerName;
    }
}
