package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import communication.messaging.interfaces.IMessageHandler;
import messages.client.DrawFromDeckMessage;
import model.interfaces.IGame;

public class DrawFromDeckMessageHandler extends MessageHandlerBase<DrawFromDeckMessage> {

    private IGame game;

    public DrawFromDeckMessageHandler(IGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(String sessionId, DrawFromDeckMessage message) {
        game.processClientDrawFromDeck(sessionId, message.getAmount());
    }
}
