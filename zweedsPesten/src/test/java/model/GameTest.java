package model;

import client.AuthenticationRestClient;
import client.IAuthRestClient;
import model.interfaces.IGame;
import model.interfaces.IPlayer;
import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import websocketserver.ServerMessageGenerator;
import websocketserver.ServerWebSocket;
import websocketserver.interfaces.IServerMessageGenerator;
import websocketserver.interfaces.IServerWebSocket;

public class GameTest {

    private IGame game;
    private IServerMessageGenerator generator;
    private IServerWebSocket serverWebSocket;
    private IAuthRestClient restClient;
    private IPlayer p1;
    private IPlayer p2;
    private IPlayer p3;

    @BeforeEach
    void initialize() {
        serverWebSocket = new ServerWebSocket();
        generator = new ServerMessageGenerator(serverWebSocket);
        restClient = new AuthenticationRestClient("http://localhost:8096/authentication");
        game = new Game(generator, restClient);
        p1 = new Player("1", "Henk");
        p1.setPassword("Test123");
        p2 = new Player("1", "Piet");
        p1.setPassword("Test123");
        p3 = new Player("1", "Jan");
        p1.setPassword("Test123");
    }

    @Ignore
    @Test
    void registerNewPlayerTest(){
        game.registerNewPlayer(p1.getSessionId(), p1.getUserName(), p1.getPassword());

    }

    @Ignore
    @Test
    void getNumberOfPlayersTest() {
        int expected = 3;

        game.registerNewPlayer(p1.getSessionId(), p1.getUserName(), p1.getPassword());

        Assertions.assertEquals(expected, game.getNumberOfPlayers());
    }
}
