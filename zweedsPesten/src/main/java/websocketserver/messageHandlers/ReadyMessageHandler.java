package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.client.ReadyMessage;
import model.interfaces.IGame;

public class ReadyMessageHandler extends MessageHandlerBase<ReadyMessage> {

    private IGame game;

    public ReadyMessageHandler(IGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(String sessionId, ReadyMessage message) {
        game.readyPlayer(sessionId, message.isReady());
    }
}
