package s3.shithead.fhict;

import communication.messaging.interfaces.IMessageHandlerFactory;
import websocketclient.ClientMessageGenerator;
import websocketclient.ClientMessageProcessor;
import websocketclient.ClientWebSocket;
import websocketclient.GameClient;
import websocketclient.interfaces.IClientMessageGenerator;
import websocketclient.interfaces.IClientMessageProcessor;
import websocketclient.interfaces.IClientWebSocket;
import websocketclient.interfaces.IGameClient;
import websocketclient.messageHandlers.ClientMessageHandlerFactory;

/**
 * Hello world!
 *
 */
public class ZweedsPestenClient
{
    public static void main( String[] args )
    {
        IClientWebSocket socket = new ClientWebSocket();
        IClientMessageGenerator generator = new ClientMessageGenerator(socket);

        IGameClient gameClient = new GameClient(generator);
        IMessageHandlerFactory factory = new ClientMessageHandlerFactory();

        IClientMessageProcessor processor = new ClientMessageProcessor(factory);
        socket.setMessageProcessor(processor);
        socket.start();
        processor.registerGameClient(gameClient);
    }

}

