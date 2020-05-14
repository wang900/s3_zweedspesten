package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.DeckCreatedMessage;
import messages.server.DrawFromDeckResultMessage;
import websocketclient.interfaces.IGameClient;

public class DeckCreatedMessageHandler extends MessageHandlerBase<DeckCreatedMessage> {

    private IGameClient gc;

    public DeckCreatedMessageHandler(IGameClient gc) {

    }

    @Override
    public void handleMessageInternal(String sessionId, DeckCreatedMessage message) {
        gc.processDeckCreated(message.getCards());
    }
}
