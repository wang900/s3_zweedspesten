package websocketclient;

import communication.messaging.interfaces.IMessageHandler;
import communication.messaging.interfaces.IMessageHandlerFactory;
import communication.messaging.MessageProcessorBase;
import websocketclient.interfaces.IClientMessageProcessor;
import websocketclient.interfaces.IGameClient;

public class ClientMessageProcessor extends MessageProcessorBase implements IClientMessageProcessor {

    private IGameClient gameClient;

    public ClientMessageProcessor(IMessageHandlerFactory messageHandlerFactory)
    {
        super(messageHandlerFactory);
    }

    public void registerGameClient(IGameClient gameClient)
    {
        this.gameClient = gameClient;
    }

    public void handleDisconnect(String sessionId)
     {
         //Do nothing
     }

    public void processMessage(String sessionId, String type, String data) {
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, gameClient);
        handler.handleMessage(data, sessionId);
    }
}
