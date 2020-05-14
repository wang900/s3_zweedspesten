package websocketserver.interfaces;

import communication.messaging.interfaces.IMessageProcessor;
import model.interfaces.IGame;

public interface IServerMessageProcessor extends IMessageProcessor {

    void registerGame(IGame game);
}
