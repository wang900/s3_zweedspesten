package s3.shithead.fhict;

import client.AuthenticationRestClient;
import client.IAuthRestClient;
import communication.messaging.interfaces.IMessageHandlerFactory;
import logging.Logger;
import model.Game;
import model.interfaces.IGame;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;
import websocketserver.ServerMessageGenerator;
import websocketserver.ServerMessageProcessor;
import websocketserver.interfaces.IServerMessageGenerator;
import websocketserver.interfaces.IServerMessageProcessor;
import websocketserver.ServerWebSocket;
import websocketserver.messageHandlers.ServerMessageHandlerFactory;

import javax.websocket.server.ServerContainer;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;

/**
 * Hello world!
 *
 */
public class ZweedsPestenServer {

    private static final int PORT = 8095;


    public static void main(String[] args) {
        startServer();
    }

    private static void startServer(){
        IMessageHandlerFactory factory = new ServerMessageHandlerFactory();
        IServerMessageProcessor messageProcessor = new ServerMessageProcessor(factory);
        final ServerWebSocket socket = new ServerWebSocket();
        socket.setMessageProcessor(messageProcessor);

        IServerMessageGenerator messageGenerator = new ServerMessageGenerator(socket);

        String restServiceUrl = "http://localhost:8096/authentication";
        IAuthRestClient restClient = new AuthenticationRestClient(restServiceUrl);

        IGame game = new Game(messageGenerator, restClient);
        messageProcessor.registerGame(game);

        Server webSocketServer = new Server();
        ServerConnector connector = new ServerConnector(webSocketServer);
        connector.setPort(PORT);
        webSocketServer.addConnector(connector);

        // Setup the basic application "context" for this application at "/"
        // This is also known as the handler tree (in jetty speak)
        ServletContextHandler webSocketContext = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webSocketContext.setContextPath("/");
        webSocketServer.setHandler(webSocketContext);

        try {
            // Initialize javax.websocket layer
            ServerContainer wscontainer = WebSocketServerContainerInitializer.configureContext(webSocketContext);

            // Add WebSocket endpoint to javax.websocket layer
            ServerEndpointConfig config = ServerEndpointConfig.Builder.create(socket.getClass(), socket.getClass().getAnnotation(ServerEndpoint.class).value())
                    .configurator(new ServerEndpointConfig.Configurator() {
                        @Override
                        public <T> T getEndpointInstance(Class<T> endpointClass) {
                            return (T) socket;
                        }
                    })
                    .build();
            wscontainer.addEndpoint(config);
            webSocketServer.start();
            webSocketServer.join();

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }
}
