package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.client.DrawFromPlayedCardsMessage;
import messages.client.PlaceCardsFromHandMessage;
import model.interfaces.IGame;

public class PlaceCardsFromHandMessageHandler extends MessageHandlerBase<PlaceCardsFromHandMessage> {

    private IGame game;

    public PlaceCardsFromHandMessageHandler(IGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(String sessionId, PlaceCardsFromHandMessage message) {
        game.processClientPlaceCardsFromHand(sessionId, message.getCards());
    }
}
