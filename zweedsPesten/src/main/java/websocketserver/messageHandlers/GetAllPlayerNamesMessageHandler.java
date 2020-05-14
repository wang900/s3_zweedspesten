package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.client.GetAllPlayerNamesMessage;
import model.interfaces.IGame;

public class GetAllPlayerNamesMessageHandler extends MessageHandlerBase<GetAllPlayerNamesMessage> {

    private IGame game;

    public GetAllPlayerNamesMessageHandler(IGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(String sessionId, GetAllPlayerNamesMessage message) {
        game.getAllPlayersInGame();
    }
}
