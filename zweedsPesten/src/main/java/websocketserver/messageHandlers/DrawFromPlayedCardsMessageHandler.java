package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.client.DrawFromDeckMessage;
import messages.client.DrawFromPlayedCardsMessage;
import model.interfaces.IGame;

public class DrawFromPlayedCardsMessageHandler extends MessageHandlerBase<DrawFromPlayedCardsMessage> {

    private IGame game;

    public DrawFromPlayedCardsMessageHandler(IGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(String sessionId, DrawFromPlayedCardsMessage message) {
        game.processClientDrawFromPlayedCards(sessionId);
    }
}
