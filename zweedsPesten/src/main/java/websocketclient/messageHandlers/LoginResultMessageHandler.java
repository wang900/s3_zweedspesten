package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.LoginResultMessage;
import websocketclient.interfaces.IGameClient;


public class LoginResultMessageHandler extends MessageHandlerBase<LoginResultMessage> {

    private IGameClient gc;

    public LoginResultMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(String sessionId, LoginResultMessage message) {
        gc.handleLoginResponse(message.getToken(), message.getPlayerName());
    }

}
