package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.PlaceCardsFromExtraHandResultMessage;
import websocketclient.interfaces.IGameClient;

public class PlaceCardsFromExtraHandResultMessageHandler extends MessageHandlerBase<PlaceCardsFromExtraHandResultMessage> {

    private IGameClient gc;

    public PlaceCardsFromExtraHandResultMessageHandler(IGameClient gc) {
        this.gc = gc;
    }

    @Override
    public void handleMessageInternal(String sessionId, PlaceCardsFromExtraHandResultMessage message) {
        gc.processPlaceCardsFromExtraHand(sessionId, message.getCards());
    }
}
