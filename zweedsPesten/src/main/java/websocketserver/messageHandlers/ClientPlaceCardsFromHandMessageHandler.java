package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.client.PlaceCardsFromHandMessage;
import model.interfaces.IGame;


public class ClientPlaceCardsFromHandMessageHandler extends MessageHandlerBase<PlaceCardsFromHandMessage> {

    private IGame game;

    public ClientPlaceCardsFromHandMessageHandler(IGame game) {
        this.game = game;
    }

    @Override
    public void handleMessageInternal(String sessionId, PlaceCardsFromHandMessage message) {
        game.processClientPlaceCardsFromHand(sessionId, message.getCards());
    }
}
