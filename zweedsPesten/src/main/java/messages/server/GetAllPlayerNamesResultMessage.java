package messages.server;

import java.util.List;

public class GetAllPlayerNamesResultMessage {

    private List<String> playerNames;

    public GetAllPlayerNamesResultMessage(List<String> playerNames) {
        this.playerNames = playerNames;
    }

    public List<String> getPlayerNames() {
        return playerNames;
    }


}
