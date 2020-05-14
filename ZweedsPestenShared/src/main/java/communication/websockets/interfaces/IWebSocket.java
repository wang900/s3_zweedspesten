package communication.websockets.interfaces;

import communication.messaging.interfaces.IMessageProcessor;

public interface IWebSocket {
    void start();

    void stop();

    void setMessageProcessor(IMessageProcessor processor);
}
