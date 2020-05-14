package websocketclient;

import communication.messaging.EncapsulatedMessage;
import communication.messaging.interfaces.IMessageProcessor;
import communication.websockets.WebSocketBase;
import logging.LogLevel;
import logging.Logger;
import serialization.SerializationProvider;
import serialization.interfaces.ISerializer;
import websocketclient.interfaces.IClientWebSocket;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;

@ClientEndpoint
public class ClientWebSocket extends WebSocketBase implements IClientWebSocket {

    private String serverUri = "ws://localhost:8095/shithead/";

    private Session session;

    private static ClientWebSocket instance = null;

    public static ClientWebSocket getInstance() {
        if (instance == null) {
            instance = new ClientWebSocket();
        }
        return instance;
    }

    @Override
    public void start() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            container.connectToServer(this, new URI(serverUri));

        } catch (Exception ex) {
            Logger.getInstance().log(ex);
        }
    }

    @Override
    public void stop() {
        try {
            if(session != null)
                session.close();

        } catch (Exception ex){
            Logger.getInstance().log(ex);
        }
    }

    @OnOpen
    public void onWebSocketConnect(Session session){
        this.session = session;
    }

    @OnMessage
    public void onWebSocketText(String message, Session session){
        onWebSocketMessageReceived(message, session.getId());
    }

    @Override
    public void send(Object object) {
        String msg = getEncapsulatedMessageGenerator().generateMessageString(object);
        sendMessageToServer(msg);
    }

    private void sendMessageToServer(String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getInstance().log(ex);
        }
    }

    public void onWebSocketMessageReceived(String message, String sessionId)
    {
        ISerializer<?> ser = SerializationProvider.getSerializer();
        EncapsulatedMessage msg = ser.deserialize(message, EncapsulatedMessage.class);
        messageProcessor.processMessage(sessionId, msg.getMessageType(), msg.getMessageData());
    }

    private IMessageProcessor messageProcessor;

    @Override
    public void setMessageProcessor(IMessageProcessor handler) {
        this.messageProcessor = handler;
    }

    @OnError
    public void onWebSocketError(Session session, Throwable cause) {
        Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason){
        session = null;
    }
}
