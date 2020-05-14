package messages.server;

public class SwitchTurnMessage {
    private String playerName;

    public SwitchTurnMessage(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }
}
