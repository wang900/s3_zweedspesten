package model.algorithm;

import model.Card;
import model.interfaces.ICard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DeckShufflerTest {

    @Test
    void shuffleTest() {
        List<ICard> cards = DeckGenerator.generateDeck();
        List<ICard> shuffledCards = DeckShuffler.shuffle(cards);

        assertNotEquals(cards, shuffledCards);
    }
}
