package model.algorithm;

import model.Card;
import model.enums.CardValue;
import model.enums.SuitType;
import model.interfaces.ICard;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeckGeneratorTest {

    @Test
    void generateDeckTest(){
        List<ICard> expected = new ArrayList<>();
        int expectedSize = 52;
        expected.add(new Card(CardValue.TWO, SuitType.CLUBS));
        expected.add(new Card(CardValue.THREE, SuitType.CLUBS));
        expected.add(new Card(CardValue.FOUR, SuitType.CLUBS));
        expected.add(new Card(CardValue.FIVE, SuitType.CLUBS));
        expected.add(new Card(CardValue.SIX, SuitType.CLUBS));
        expected.add(new Card(CardValue.SEVEN, SuitType.CLUBS));
        expected.add(new Card(CardValue.EIGHT, SuitType.CLUBS));
        expected.add(new Card(CardValue.NINE, SuitType.CLUBS));
        expected.add(new Card(CardValue.TEN, SuitType.CLUBS));
        expected.add(new Card(CardValue.JACK, SuitType.CLUBS));
        expected.add(new Card(CardValue.QUEEN, SuitType.CLUBS));
        expected.add(new Card(CardValue.KING, SuitType.CLUBS));
        expected.add(new Card(CardValue.ACE, SuitType.CLUBS));

        List<ICard> actual = DeckGenerator.generateDeck();

        assertEquals(expected.get(0).toString(), actual.get(0).toString());
        assertEquals(expected.get(1).toString(), actual.get(1).toString());
        assertEquals(expected.get(2).toString(), actual.get(2).toString());
        assertEquals(expected.get(3).toString(), actual.get(3).toString());
        assertEquals(expected.get(4).toString(), actual.get(4).toString());
        assertEquals(expected.get(5).toString(), actual.get(5).toString());
        assertEquals(expected.get(6).toString(), actual.get(6).toString());
        assertEquals(expected.get(7).toString(), actual.get(7).toString());
        assertEquals(expected.get(8).toString(), actual.get(8).toString());
        assertEquals(expected.get(9).toString(), actual.get(9).toString());
        assertEquals(expected.get(10).toString(), actual.get(10).toString());
        assertEquals(expected.get(11).toString(), actual.get(11).toString());
        assertEquals(expected.get(12).toString(), actual.get(12).toString());
        assertEquals(expectedSize, actual.size());
    }
}
