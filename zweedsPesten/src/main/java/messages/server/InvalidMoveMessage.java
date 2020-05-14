package messages.server;

import model.enums.GameAction;

public class InvalidMoveMessage{

    private GameAction gameAction;

    public InvalidMoveMessage(GameAction gameAction) {
        this.gameAction = gameAction;
    }

    public GameAction getGameAction() {
        return gameAction;
    }
}
