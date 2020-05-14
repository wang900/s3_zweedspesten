package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.DrawFromPlayedCardsResultMessage;
import websocketclient.interfaces.IGameClient;

public class DrawFromPlayedCardsResultMessageHandler extends MessageHandlerBase<DrawFromPlayedCardsResultMessage> {

    private IGameClient gc;

    public DrawFromPlayedCardsResultMessageHandler(IGameClient gc) {
        this.gc = gc;
    }

    @Override
    public void handleMessageInternal(String sessionId, DrawFromPlayedCardsResultMessage message) {
        gc.processDrawCardsFromPlayedCardsResult(message.getPlayerName(), message.getCards());
    }
}
