package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.InvalidMoveMessage;
import websocketclient.interfaces.IGameClient;

public class InvalidMoveMessageHandler extends MessageHandlerBase<InvalidMoveMessage> {

    private IGameClient gc;

    public InvalidMoveMessageHandler(IGameClient gc) {
        this.gc = gc;
    }

    @Override
    public void handleMessageInternal(String sessionId, InvalidMoveMessage message) {
        gc.processInvalidMove(sessionId, message.getGameAction());
    }
}
