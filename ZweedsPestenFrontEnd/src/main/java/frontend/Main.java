package frontend;

import communication.messaging.interfaces.IMessageHandlerFactory;
import frontend.controllers.ControllerHelper;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import websocketclient.ClientMessageGenerator;
import websocketclient.ClientMessageProcessor;
import websocketclient.ClientWebSocket;
import websocketclient.GameClient;
import websocketclient.interfaces.IClientMessageGenerator;
import websocketclient.interfaces.IClientMessageProcessor;
import websocketclient.interfaces.IClientWebSocket;
import websocketclient.interfaces.IGameClient;
import websocketclient.messageHandlers.ClientMessageHandlerFactory;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{

        IClientWebSocket socket = new ClientWebSocket();
        IClientMessageGenerator generator = new ClientMessageGenerator(socket);

        IGameClient gameClient = new GameClient(generator);
        IMessageHandlerFactory factory = new ClientMessageHandlerFactory();

        IClientMessageProcessor processor = new ClientMessageProcessor(factory);
        socket.setMessageProcessor(processor);

        socket.start();

        processor.registerGameClient(gameClient);
        ControllerHelper.getInstance().setGameClient(gameClient);
        primaryStage.getIcons().add(new Image("/pictures/icon.png"));
        ControllerHelper.getInstance().setPrimaryStage(primaryStage);
        ControllerHelper.getInstance().loadFxml("Login", "/login.fxml");
    }



    public static void main(String[] args) {
        launch(args);
    }
}
