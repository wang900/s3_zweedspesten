package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.RoundResultMessage;
import websocketclient.interfaces.IGameClient;

public class GameSessionResultMessageHandler extends MessageHandlerBase<RoundResultMessage> {

    private IGameClient gc;

    public GameSessionResultMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(String sessionId, RoundResultMessage message) {
        gc.processGameSessionResult(message.getWinner());
    }
}
