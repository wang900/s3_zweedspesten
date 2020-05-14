package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.client.PlaceCardsFromExtraHandMessage;
import model.interfaces.IGame;

public class PlaceCardsFromExtraHandMessageHandler extends MessageHandlerBase<PlaceCardsFromExtraHandMessage> {

    private IGame game;

    public PlaceCardsFromExtraHandMessageHandler(IGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(String sessionId, PlaceCardsFromExtraHandMessage message) {
        game.processClientPlaceCardsFromExtraHand(sessionId, message.getCards());
    }
}
