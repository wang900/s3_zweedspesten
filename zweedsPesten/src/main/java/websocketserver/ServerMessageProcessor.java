package websocketserver;

import communication.messaging.interfaces.IMessageHandler;
import communication.messaging.interfaces.IMessageHandlerFactory;
import communication.messaging.MessageProcessorBase;
import model.interfaces.IGame;
import websocketserver.interfaces.IServerMessageProcessor;

public class ServerMessageProcessor extends MessageProcessorBase implements IServerMessageProcessor {

    private IGame game;

    public ServerMessageProcessor(IMessageHandlerFactory messageHandlerFactory)
    {
        super(messageHandlerFactory);
    }



    public IGame getGame()
    {
        return game;
    }

    public void processMessage(String sessionId, String type, String data) {
        //Get the last part from the full package and type name
        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getGame());
        handler.handleMessage(data, sessionId);
    }

    public void handleDisconnect(String sessionId)
    {
        getGame().processClientDisconnect(sessionId);
    }

    @Override
    public void registerGame(IGame game)
    {
        this.game = game;
    }
}
