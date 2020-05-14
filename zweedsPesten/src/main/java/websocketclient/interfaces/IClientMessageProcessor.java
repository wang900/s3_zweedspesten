package websocketclient.interfaces;

import communication.messaging.interfaces.IMessageProcessor;

public interface IClientMessageProcessor extends IMessageProcessor {

    void registerGameClient(IGameClient gameClient);
}
