package communication.messaging.interfaces;

public interface IMessageHandler {

    void handleMessage(String data, String sessionId);
}
