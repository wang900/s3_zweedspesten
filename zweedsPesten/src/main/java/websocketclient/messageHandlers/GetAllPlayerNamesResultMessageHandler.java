package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.GetAllPlayerNamesResultMessage;
import websocketclient.interfaces.IGameClient;

public class GetAllPlayerNamesResultMessageHandler  extends MessageHandlerBase<GetAllPlayerNamesResultMessage> {

    private IGameClient gc;

    public GetAllPlayerNamesResultMessageHandler(IGameClient client)
    {
        this.gc = client;
    }

    @Override
    public void handleMessageInternal(String sessionId, GetAllPlayerNamesResultMessage message) {
        gc.handleGetPlayerNamesResponse(message.getPlayerNames());
    }
}
