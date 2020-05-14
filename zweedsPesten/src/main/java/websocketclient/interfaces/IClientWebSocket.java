package websocketclient.interfaces;

import communication.websockets.interfaces.IWebSocket;

public interface IClientWebSocket extends IWebSocket {

    void send(Object object);

    void onWebSocketMessageReceived(String message, String sessionId);
}
