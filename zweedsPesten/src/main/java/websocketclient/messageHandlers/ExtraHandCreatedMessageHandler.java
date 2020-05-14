package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.DrawFromDeckResultMessage;
import messages.server.ExtraHandCreatedMessage;
import websocketclient.interfaces.IGameClient;

public class ExtraHandCreatedMessageHandler extends MessageHandlerBase<ExtraHandCreatedMessage> {

    private IGameClient gc;

    public ExtraHandCreatedMessageHandler(IGameClient gc) {

    }

    @Override
    public void handleMessageInternal(String sessionId, ExtraHandCreatedMessage message) {
        gc.processExtraHandCreated(message.getPlayerName(), message.getCards());
    }
}
