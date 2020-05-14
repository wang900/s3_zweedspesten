package model;

import model.interfaces.IPlayer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {

    private IPlayer player;
    private String userName = "test";
    private String sessionId = "test123";

    @BeforeEach
    void initialize()
    {
        player = new Player(sessionId, userName);
    }

    @Test
    void getConstructorData()
    {
       assertEquals(userName, player.getUserName());
       assertEquals(sessionId, player.getSessionId());
    }

    @Test
    void setPassword(){
        String expectedPassword = "132Test";
        player.setPassword(expectedPassword);

        assertEquals(expectedPassword, player.getPassword());
    }

    @Test
    void isReadyTrue() {
        player.setReady(true);

        assertTrue(player.isReady());
    }

    @Test
    void isReadyFalse() {

        assertFalse(player.isReady());
    }
}
