package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.PlayerHasRegisteredMessage;
import websocketclient.interfaces.IGameClient;

public class PlayerHasRegisteredMessageHandler extends MessageHandlerBase<PlayerHasRegisteredMessage> {

    private IGameClient gc;

    public PlayerHasRegisteredMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(String sessionId, PlayerHasRegisteredMessage message) {
        gc.handlePlayerRegistered(message.getPlayerName());
    }
}
