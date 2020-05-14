package model;

import model.enums.CardValue;
import model.enums.SuitType;
import model.interfaces.ICard;
import model.interfaces.IDeck;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    private IDeck deck;

    @BeforeEach
    void initialize(){
        deck = new Deck();
    }

    @Test
    void createDeckTest(){
        assertTrue(deck.createDeck());
        assertNotNull(deck.getCards());
    }

//    @Test
//    void takeCardsTest() {
//        deck.createDeck();
//
//        List<ICard> expected = new ArrayList<>();
//
//        expected.add((deck.getCards().get(0)));
//        expected.add((deck.getCards().get(1)));
//        expected.add((deck.getCards().get(2)));
//        List<ICard> result = deck.drawCards(3);
//
//        assertEquals(expected.size(), result.size());
//        assertEquals(expected.get(0), result.get(0));
//        assertEquals(expected.get(1), result.get(1));
//        assertEquals(expected.get(2), result.get(2));
//    }

    @Test
    void reshuffleDeckTest(){
        deck.createDeck();
        List<ICard> cards = new ArrayList<>(deck.getCards());
        assertTrue(deck.reshuffleDeck());
        assertNotEquals(cards, deck.getCards());
    }
}
