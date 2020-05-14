package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.client.RegisterPlayerMessage;
import model.interfaces.IGame;

public class RegisterPlayerMessageHandler extends MessageHandlerBase<RegisterPlayerMessage> {

    private IGame game;

    public RegisterPlayerMessageHandler(IGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(String sessionId, RegisterPlayerMessage message) {
        game.registerNewPlayer(sessionId, message.getPlayerName(), message.getPassword());
    }
}
