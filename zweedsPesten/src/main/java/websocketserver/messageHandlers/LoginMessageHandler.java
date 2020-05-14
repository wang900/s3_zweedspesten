package websocketserver.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.client.LoginMessage;
import model.interfaces.IGame;


public class LoginMessageHandler extends MessageHandlerBase<LoginMessage> {

    private IGame game;

    public LoginMessageHandler(IGame game) { this.game = game;}

    @Override
    public void handleMessageInternal(String sessionId, LoginMessage message) {
        game.loginPlayer(sessionId, message.getPlayerName(), message.getPassword());
    }
}
