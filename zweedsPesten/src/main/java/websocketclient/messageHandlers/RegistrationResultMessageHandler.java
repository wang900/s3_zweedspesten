package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.RegistrationResultMessage;
import websocketclient.interfaces.IGameClient;

public class RegistrationResultMessageHandler extends MessageHandlerBase<RegistrationResultMessage> {

    private IGameClient gc;

    public RegistrationResultMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(String sessionId, RegistrationResultMessage message) {
        gc.handlePlayerRegistrationResponse(message.isResult());
    }

}
