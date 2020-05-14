package websocketserver.messageHandlers;

import communication.messaging.interfaces.IMessageHandler;
import communication.messaging.interfaces.IMessageHandlerFactory;
import messages.client.DrawFromDeckMessage;
import messages.client.PlaceCardsFromHandMessage;
import model.interfaces.IGame;

public class ServerMessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object game) {
        IGame igame = (IGame) game;
        switch (simpleType) {
            case "RegisterPlayerMessage":
                return new RegisterPlayerMessageHandler(igame);
            case "ClientPlaceCardsFromHand":
                return new ClientPlaceCardsFromHandMessageHandler(igame);
            case "LoginMessage":
                return new LoginMessageHandler(igame);
            case "ReadyMessage":
                return new ReadyMessageHandler(igame);
            case "GetAllPlayerNamesMessage":
                return new GetAllPlayerNamesMessageHandler(igame);
            case "DrawFromDeckMessage":
                return new DrawFromDeckMessageHandler(igame);
            case "DrawFromPlayedCardsMessage":
                return new DrawFromPlayedCardsMessageHandler(igame);
            case "PlaceCardsFromHandMessage":
                return new PlaceCardsFromHandMessageHandler(igame);
            case "PlaceCardsFromExtraHandMessage":
                return new PlaceCardsFromExtraHandMessageHandler(igame);
            default:
                return null;
        }
    }
}
