package communication.messaging.interfaces;

public interface IMessageHandlerFactory {

    IMessageHandler getHandler(String simpleType, Object game);

}
