package model;

import model.enums.CardValue;
import model.enums.SuitType;
import model.interfaces.IHand;
import model.interfaces.IPlayedCards;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayedCardsTest {

    private IHand hand;
    private IPlayedCards playedCards;

    @BeforeEach
    void initialize(){
            playedCards = new PlayedCards(new BanishedCards());
            hand = new Hand(new Player("test", "test"), playedCards);
    }

    @Test
    void getConstructorData() {
            assertNotNull(playedCards.getCards());
    }

    @Test
    void drawCards() {
        playedCards.addCard(new Card(CardValue.FIVE, SuitType.SPADES));
        playedCards.drawCards(hand);
        assertEquals(1, hand.getCards().size());
        assertEquals(0, playedCards.getCards().size());
    }
}
