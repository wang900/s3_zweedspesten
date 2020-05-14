package websocketclient.messageHandlers;

import communication.messaging.interfaces.IMessageHandler;
import communication.messaging.interfaces.IMessageHandlerFactory;
import websocketclient.interfaces.IGameClient;

public class ClientMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object game) {
        IGameClient gc = (IGameClient)game;

        switch(simpleType) {
            case "RegistrationResultMessage":
                return new RegistrationResultMessageHandler(gc);
            case "PlayerHasRegisteredMessage":
                return new PlayerHasRegisteredMessageHandler(gc);
            case "StartGameSessionMessage":
                return new StartGameSessionMessageHandler(gc);
            case "GameSessionResultMessage":
                return new GameSessionResultMessageHandler(gc);
            case "LoginResultMessage":
                return new LoginResultMessageHandler(gc);
            case "ReadyResultMessage":
                return new ReadyResultMessageHandler(gc);
            case "GetAllPlayerNamesResultMessage":
                return new GetAllPlayerNamesResultMessageHandler(gc);
            case "DrawFromDeckResultMessage":
                return new DrawFromDeckResultMessageHandler(gc);
            case "DrawFromPlayedCardsResultMessage":
                return new DrawFromPlayedCardsResultMessageHandler(gc);
            case "PlaceCardsFromExtraHandResultMessage":
                return new PlaceCardsFromExtraHandResultMessageHandler(gc);
            case "PlaceCardsFromHandResultMessageHandler":
                return new PlaceCardsFromHandResultMessageHandler(gc);
            case "InvalidMoveMessage":
                return new InvalidMoveMessageHandler(gc);
            case "DeckCreatedMessage":
                return new DeckCreatedMessageHandler(gc);
            case "ExtraHandCreatedMessage":
                return new ExtraHandCreatedMessageHandler(gc);
            default:
                return null;
        }

    }
}
