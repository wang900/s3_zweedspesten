package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.DrawFromDeckResultMessage;
import websocketclient.interfaces.IGameClient;

public class DrawFromDeckResultMessageHandler extends MessageHandlerBase<DrawFromDeckResultMessage> {

    private IGameClient gc;

    public DrawFromDeckResultMessageHandler(IGameClient gc) {

    }

    @Override
    public void handleMessageInternal(String sessionId, DrawFromDeckResultMessage message) {
        gc.processDrawCardsFromDeck(message.getPlayerName(), message.getCards());
    }
}
