package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.StartGameSessionMessage;
import websocketclient.interfaces.IGameClient;

public class StartGameSessionMessageHandler extends MessageHandlerBase<StartGameSessionMessage> {

    private IGameClient gc;

    public StartGameSessionMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(String sessionId, StartGameSessionMessage message) {
        gc.processGameSessionStarted();
    }
}
