package websocketclient.messageHandlers;

import communication.messaging.MessageHandlerBase;
import messages.server.ReadyResultMessage;
import websocketclient.interfaces.IGameClient;

public class ReadyResultMessageHandler extends MessageHandlerBase<ReadyResultMessage>  {

    private IGameClient gc;

    public ReadyResultMessageHandler(IGameClient gc) {
        this.gc = gc;
    }

    @Override
    public void handleMessageInternal(String sessionId, ReadyResultMessage message) {
        gc.processReadyResult(message.getMap());
    }


}
