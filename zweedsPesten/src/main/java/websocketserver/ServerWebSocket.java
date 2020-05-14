package websocketserver;

import communication.messaging.EncapsulatedMessage;
import communication.messaging.interfaces.IMessageProcessor;
import communication.websockets.WebSocketBase;
import logging.LogLevel;
import logging.Logger;
import websocketserver.interfaces.IServerWebSocket;

import javax.inject.Singleton;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Singleton
@ServerEndpoint(value="/shithead/")
public class ServerWebSocket extends WebSocketBase implements IServerWebSocket {

        private IMessageProcessor messageProcessor;

        public void setMessageProcessor(IMessageProcessor processor) {
            this.messageProcessor = processor;
        }
        private IMessageProcessor getMessageProcessor() {
            return messageProcessor;
        }

        private static ArrayList<Session> sessions = new ArrayList<>();

        @OnOpen
        public void onConnect(Session session) {
            Logger.getInstance().log("[WebSocket Connected] SessionID: " + session.getId(), LogLevel.INFORMATION);
            sessions.add(session);
            Logger.getInstance().log("[#sessions]: " + sessions.size(), LogLevel.INFORMATION);
        }

        @OnMessage
        public void onText(String message, Session session) {
            String sessionId = session.getId();
            EncapsulatedMessage msg = getSerializer().deserialize(message, EncapsulatedMessage.class);
            getMessageProcessor().processMessage(sessionId, msg.getMessageType(), msg.getMessageData());
        }

        @OnClose
        public void onClose(CloseReason reason, Session session) {
            getMessageProcessor().handleDisconnect(session.getId());
            sessions.remove(session);
        }

        @OnError
        public void onError(Throwable cause, Session session) {
            Logger.getInstance().log(cause.getMessage(), LogLevel.ERROR);
        }

        public void sendTo(String sessionId, Object object) {
            String msg = getEncapsulatedMessageGenerator().generateMessageString(object);
            sendToClient(getSessionFromId(sessionId), msg);
        }

        @Override
        public void stop() {
            //START AND STOP ARE HANDLED ELSEWHERE
        }

        @Override
        public void start() {
            //START AND STOP ARE HANDLED ELSEWHERE
        }

    private Session getSessionFromId(String sessionId)
    {
        for(Session s : sessions)
        {
            if(s.getId().equals(sessionId))
                return s;
        }
        return null;
    }

    public void broadcast(Object object)
    {
        for(Session session : sessions) {
            sendTo(session.getId(), object);
        }
    }

    public void sendToOthers(String sessionId, Object object)
    {
        Session session = getSessionFromId(sessionId);
        for(Session ses : sessions) {
            if(!ses.getId().equals(Objects.requireNonNull(session).getId()))
                sendTo(ses.getId(), object);
        }
    }

    private void sendToClient(Session session, String message)
    {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException ex) {
            Logger.getInstance().log(ex);
        }
    }
}
