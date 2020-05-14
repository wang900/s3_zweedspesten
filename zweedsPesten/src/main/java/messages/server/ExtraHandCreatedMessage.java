package messages.server;

import model.interfaces.ICard;

import java.util.List;

public class ExtraHandCreatedMessage {

    private String playerName;
    private List<ICard> cards;

    public ExtraHandCreatedMessage(String playerName, List<ICard> cards) {
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
