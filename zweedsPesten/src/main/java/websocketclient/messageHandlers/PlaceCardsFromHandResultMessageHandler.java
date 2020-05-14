package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.PlaceCardsFromHandResultMessage;
import websocketclient.interfaces.IGameClient;

public class PlaceCardsFromHandResultMessageHandler extends MessageHandlerBase<PlaceCardsFromHandResultMessage> {

    private IGameClient gc;

    public PlaceCardsFromHandResultMessageHandler(IGameClient gc) {
        this.gc = gc;
    }

    @Override
    public void handleMessageInternal(String sessionId, PlaceCardsFromHandResultMessage message) {
        gc.processPlaceCardsFromHand(message.getPlayerName(), message.getCards());
    }
}
